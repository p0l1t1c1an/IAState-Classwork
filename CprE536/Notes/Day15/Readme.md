
### Files
- File size versus Frequency modified
- Smaller more commonly open and edited
- Analysis to find files of interest

### File Carving
- recovery of files from digital storage
- unrecoverable conventionally
- File system doesn't delete data
- Just says blocks aren't being used
    - inode and file table entry
- File pieces many not be stored in continuous blocks

### Problems
- Giant jigswaw
- One solution
    - Brute force O(n!)
- 1TB drive with blocks of 4KB
    - 1TB/4KB == 2.6 * $10^8$
    - $10^8$! > $10^100000$

### Solutions
- Known File type sturctures to classify and piece together files
- Devise likeness weight between blocks to guess liklihood of blocks being adjacent data 
- Classify by type of data
- Carving
    - Compile library of known file headers and footers
    - Comb through clusters searching for headers
    - Match header with corresponding footer
    - Recover everything between as target file

### Foremost
- Takes file header and footer in aconfig
- Go through disk to find headers
- Builds buffer up max file size
- If footer is found, carver file from header to footer
- Else cave maximum file size
- Continue search just past where header was found


### Fast but not robust
- File systems tools are fast and able to extract most files
- Depend on data not being fragmented
- Generally aren't but most likely are: 
    - 16% of jpegs
    - 17% of word docs
    - 22% of AVI
    - 58% of PST MS Outlook
- More of a problem what low on disk space

### Graph Approach
- Reconstruct files as a shortest longest path problem
- Blocks are vertices in graph
- weights represent how near/far two blocks are from each other
- Fragmentation is irrelevant

### Assgning Edge Weights
- Text files
    - Sliding window seqeunce likelihood
    - Block 1: "Hi to the wor"
    - Block 2: "old, how are "
- Image 
    - Pixel neighbor differenc

### Graph Algorithm
- Wight = semanticLikeness(bi, bj)
- Maximize total cost for trip that touches every node exactly once
    - Hamiltonian path
- K verx-disjoint-path 
    - Maximize K disjoint paths where K = number of file headers
        - shortest path first
            - construct and remove minimal paths
        - parallel unique path
            - grow all files at once, append best match at each step
- Good but not efficient
- SPF == 88% files
- PUP == 85% files
- Slow as well
    - O($n^2$ & log(n))

### Bifragment Gap Carving
- Most files only split into 2 frags
- Use structure validation to id bifraged blocks
- Approach
    - Locate corresponding file header and footer no more than M clusters apart
    - Attempt to validate the file over all gap sizes betwwen header and footer
    - Successful validiation is strong indicator of correct carve
- Can't handle distant blocks
    - O(M^2) validations 
- Not scaled to large numbers or frags
- Validation does not guarentee good carve
- Cannot be applied to plaintext or BMPs as they can't be validated
- Lost or distant clusters will result in worst case time


### Encrypted file
- Very high entropy in encrypted files
- We can determine if a block is encrypted if it has high entropy
- Can extract encrypted blocks
- Still can't decrypt to get files

