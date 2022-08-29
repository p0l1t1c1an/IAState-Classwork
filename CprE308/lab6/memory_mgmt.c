#include <stdio.h>
#include <stdlib.h>

#define NUM_FRAMES      16      /* Number of Page Frames */
#define NUM_PAGES       128     /* Number of memory pages. 
				   The pages are numbered 1..NUM_PAGES */
#define NUM_ACCESSES    10000    /* Number of memory accesses */
#define NUM_RUNS	50	/* Number of times to run each case */

int seed = 0;

/* Structure to hold a page information */
typedef struct {
	int page_id;  /* The page ID number. If no page exits, it is set to -1 */
	int time_of_access;  /* Time when the page was last accessed */
	int time_of_arrival; /* Time when the page was brought in the memory */
} PageFrame;

/* Structure to hold page fault totals */
typedef struct {
	int page_faults_FIFO_seq;
	int page_faults_LRU_seq;
	int page_faults_OPT_seq;

	int page_faults_FIFO_rand;
	int page_faults_LRU_rand;
	int page_faults_OPT_rand;

	int page_faults_FIFO_lr;
	int page_faults_LRU_lr;
	int page_faults_OPT_lr;
} PageFaultTotal;

  

/* Function pointer to the handler for the page replacement algorithm */
typedef int (* PRAlgoType) (const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access);

/* Memory management Handler Algorithms prototypes */
int PRAlgo_FIFO(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access);
int PRAlgo_LRU(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access);
int PRAlgo_OPT(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access);


/* Initialize the page frames, set all values to -1 */
void initialize_page_frames(PageFrame * PageFrames, int size)
{
	int i;

	for(i = 0; i < size; i++)
	{
		PageFrames[i].page_id = -1;
		PageFrames[i].time_of_access = -1;
		PageFrames[i].time_of_arrival = -1;
	}

	return;
}

/* Functions to generate page request data */

/* Generate a sequence of random page accesses. */
int * build_random_access_seq(int * PageAccesses, int num_accesses)
{
	int i;  

	srand(seed); 

	for(i=0; i < num_accesses; i++)
		PageAccesses[i] = (rand() % NUM_PAGES) + 1;   

	return PageAccesses;
}


/* Sequential page access */
int * build_sequential_access_seq(int * PageAccesses, int num_accesses)
{
	int i;

	for(i=0; i<num_accesses; i++)        
		PageAccesses[i] = (i % NUM_PAGES) + 1;

	return PageAccesses;
}


/* LR_workload page access */
int * build_lr_workload_access_seq(int * PageAccesses, int num_accesses)
{
	int i;  
	time_t t;  

	srand(seed); 

	for(i=0; i<num_accesses; i++)
	{   
		if(i<5)                         /* Assign the first five just randomly */      
			PageAccesses[i] = (rand() % NUM_PAGES) + 1;

		else if((rand() % 100) <90)     /* Then assign based on history for 90% cases */
			PageAccesses[i] = PageAccesses[i-(rand()%5)-1]; 

		else
			PageAccesses[i] = (rand() % NUM_PAGES) + 1; /* Assign randomly for the rest 20% cases */       
	}

	return PageAccesses;
}


/* Function to handle the page accesses. It uses the Page replacement Handlers. Returns the number of page faults */
int handle_page_accesses(PageFrame * PageFrames, int num_frames, int * PageAccesses, int num_accesses, PRAlgoType PRAlgo)
{
	int i;
	int next_page = 0; /* Page to be read */
	int page_faults = 0; /* No of page faults */
	int current_access = 0;  /* The current time of access */
	
	/* Check for all memory accesses one by one */
	for(current_access=0; current_access < num_accesses; current_access++)
	{   
		next_page = PageAccesses[current_access]; /* Read the next page access */
				
		int page_to_be_replaced = -1;  /* Set to index of the page to be replaced */
		int go_to_next_page_access = 0;

		/* Now search for the page in the memory */
		for(i=0; i<num_frames; i++)
		{
			if(next_page == PageFrames[i].page_id)
			{	
				/* Page in memory, just update the time of access */	
				PageFrames[i].time_of_access = current_access;
				go_to_next_page_access = 1; 
				break;	
			}
		}

		if(go_to_next_page_access)  /* No fault, so check for the next access */
			continue;
		
		/* Page Fault as Page was not in memory */
		page_faults++;
			
		/* First check for free memory */
		for(i=0; i<num_frames; i++)
		{
			if(PageFrames[i].page_id == -1)
			{
				/* Empty memory location found. Insert Frame in memory */	
				PageFrames[i].page_id = next_page;
				PageFrames[i].time_of_access  = current_access;
				PageFrames[i].time_of_arrival = current_access;
				
				go_to_next_page_access = 1; 
				break;
			}
		}
			
		
		if(go_to_next_page_access)  /* No need for replacement, so check for the next access */
			continue;
		
		/* No free memory! Find a victim to replace in the memory using the handler */    
		page_to_be_replaced = PRAlgo(PageFrames,num_frames,PageAccesses,num_accesses,current_access);
		
		PageFrames[page_to_be_replaced].page_id = next_page;
		PageFrames[page_to_be_replaced].time_of_access = current_access;
		PageFrames[page_to_be_replaced].time_of_arrival = current_access;    
	
	}
	
	return page_faults;
}


int main ()
{  
	int PageAccesses[NUM_ACCESSES];    /* Array to store the page accesses */
	PageFrame PageFrames[NUM_FRAMES];    /* Array representing the page frame */
	PageFaultTotal PageFaultTotals;	/* Track page fault totals across runs */
	/* Initialize all page faults for all cases to 0 */
	PageFaultTotals.page_faults_FIFO_seq = 0;
	PageFaultTotals.page_faults_LRU_seq = 0;
	PageFaultTotals.page_faults_OPT_seq = 0;
	PageFaultTotals.page_faults_FIFO_rand = 0;
	PageFaultTotals.page_faults_LRU_rand = 0;
	PageFaultTotals.page_faults_OPT_rand = 0;
	PageFaultTotals.page_faults_FIFO_lr = 0;
	PageFaultTotals.page_faults_LRU_lr = 0;
	PageFaultTotals.page_faults_OPT_lr = 0;
	
	int i;
	for(i = 0; i < NUM_RUNS; i++)
	{
		/* Memory access analysis with random access */
		initialize_page_frames(PageFrames,NUM_FRAMES);
		build_random_access_seq(PageAccesses,NUM_ACCESSES);

        PageFaultTotals.page_faults_FIFO_rand += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_FIFO);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_LRU_rand += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_LRU);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_OPT_rand += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_OPT);
	
	
		/* Memory access analysis with sequential access */
		initialize_page_frames(PageFrames,NUM_FRAMES);
		build_sequential_access_seq(PageAccesses,NUM_ACCESSES); 
		PageFaultTotals.page_faults_FIFO_seq += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_FIFO);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_LRU_seq += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_LRU);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_OPT_seq += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_OPT);

	
		/* Memory access analysis with LR workload access */
		initialize_page_frames(PageFrames,NUM_FRAMES);
		build_lr_workload_access_seq(PageAccesses,NUM_ACCESSES);
		PageFaultTotals.page_faults_FIFO_lr += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_FIFO);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_LRU_lr += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_LRU);
		initialize_page_frames(PageFrames,NUM_FRAMES);
		PageFaultTotals.page_faults_OPT_lr += handle_page_accesses(PageFrames,NUM_FRAMES,PageAccesses,NUM_ACCESSES,PRAlgo_OPT);
		
		seed++;		// Increment seed to generate new sequence of accesses next iteration
	}

	printf("The average number of page faults for FIFO with Random Access is %d.\n",PageFaultTotals.page_faults_FIFO_rand / NUM_RUNS);
	printf("The average number of page faults for LRU with Random Access is %d.\n",PageFaultTotals.page_faults_LRU_rand / NUM_RUNS);
	printf("The average number of page faults for OPT with Random Access is %d.\n",PageFaultTotals.page_faults_OPT_rand / NUM_RUNS);

	printf("The average number of page faults for FIFO with Sequential Access is %d.\n",PageFaultTotals.page_faults_FIFO_seq / NUM_RUNS);
	printf("The average number of page faults for LRU with Sequential Access is %d.\n",PageFaultTotals.page_faults_LRU_seq / NUM_RUNS);
	printf("The average number of page faults for OPT with Sequential Access is %d.\n",PageFaultTotals.page_faults_OPT_seq / NUM_RUNS);

	printf("The average number of page faults for FIFO with LR Workload Access is %d.\n",PageFaultTotals.page_faults_FIFO_lr / NUM_RUNS);
	printf("The average number of page faults for LRU with LR Workload Access is %d.\n",PageFaultTotals.page_faults_LRU_lr / NUM_RUNS);
	printf("The average number of page faults for OPT with LR Workload Access is %d.\n",PageFaultTotals.page_faults_OPT_lr / NUM_RUNS);

	return 0;
}


/*
 * Memory allocation on the basis of Time of Arrival
 * PageFrames = The current table
 * num_frames = how many frames in you table
 * PageAccesses = id of pages in the order of the access, index is the time of access i.e., PageAccesses[i]=is the id of the page accessed at time i
 * num_accesses = How many access is in the PageAccesses
 * current_access = is the current time, so PageAccesses[current_access] = is the id of the page we want to put in the table right now
 * Return: index of the PageFrame in the table that is going to be replaced to fix the page fault
 */
int PRAlgo_FIFO(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access)
{
    (void) PageAccesses;
    (void) num_accesses;
    (void) current_access;

	int frame_to_evict = 0;
	int i;
    for (i = 1; i < num_frames; i++) {
	    if(PageFrames[frame_to_evict].time_of_arrival > PageFrames[i].time_of_arrival)
        {
            frame_to_evict = i;
        }
	}

	return frame_to_evict;
}

/*
 * Memory allocation on the basis of Recent Access Time
 * PageFrames = The current table
 * num_frames = how many frames in you table
 * PageAccesses = id of pages in the order of the access, index is the time of access i.e., PageAccesses[i]=is the id of the page accessed at time i
 * num_accesses = How many access is in the PageAccesses
 * current_access = is the current time, so PageAccesses[current_access] = is the id of the page we want to put in the table right now
 * Return: index of the PageFrame in the table that is going to be replaced to fix the page fault
 */
int PRAlgo_LRU(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access)
{
    (void) PageAccesses;
    (void) num_accesses;
    (void) current_access;
 
    int frame_to_evict = 0;
    int i;
    for (i = 1; i < num_frames; i++) {
	    if(PageFrames[frame_to_evict].time_of_access > PageFrames[i].time_of_access)
        {
            frame_to_evict = i;
        }
    }	

	return frame_to_evict;

}


/*
 * Memory allocation on the basis of Future Access Time
 * PageFrames = The current table
 * num_frames = how many frames in you table
 * PageAccesses = id of pages in the order of the access, index is the time of access i.e., PageAccesses[i]=is the id of the page accessed at time i
 * num_accesses = How many access is in the PageAccesses
 * current_access = is the current time, so PageAccesses[current_access] = is the id of the page we want to put in the table right now
 * Return: index of the PageFrame in the table that is going to be replaced to fix the page fault
 */
int PRAlgo_OPT(const PageFrame * PageFrames, int num_frames, const int * PageAccesses, int num_accesses, int current_access)
{
    int next_access[num_frames];
    int frame_to_evict = 0;
    
    int i, k;
    for(i = 0; i < num_frames; i++) {
        next_access[i] = -1;
        for(k = current_access; k < num_accesses; k++) {
            if(PageFrames[i].page_id == PageAccesses[k]) {
                next_access[i] = k;
                break;
            }
        }
    }

    for(i = 0; i < num_frames; i++) {
        if(next_access[i] == -1) {
            return i;
        } else if(next_access[frame_to_evict] < next_access[i]) {
            frame_to_evict = i;
        }
    }

	return frame_to_evict;

}
