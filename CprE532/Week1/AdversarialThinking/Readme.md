
# Adversarial Thinking

Thinking like an attacker

### What is it?
- Adversarial thinking
    - mindset where you think about how someone could disrupt, damage or make unusable a system or process
- Often think about non malicious threats (weather ... power outages)
    - SRE does this
    - These events can be used to carry out other attacks
    - What about malicious threats carried out by a determined adversary
    - Physical access (throughout history)
    - Cyber enabled access (newest threat vector)
- We need to approach our solutions asking, **“What if an adversary had access?”**
    - Then what changes could we make to make it harder for the adversary to be successful
- ***We need to think like the adversaries***


### Historic Defense

- Primary Defense systems
- 1990s
    - Firewalls – 1980s
    - Encryption WW2
    - Detection  IDS – 1985
    - Secure OS – Secure Unix late 1980s
    - User awareness mid 1990s
- Today
    - Minor Improvements on these
    - Nothing much new


### What changed in 30 years
- More  attackers
- More possible devices (over 30 billion)
- More motivations to attack ($, IP, war)
- More reliance on technology and the Internet
- More potential victims (users on the net)
- More DATA to steal



### What is our risk?
- We don’t know how important something is until we lose it.
- We don’t always know what is important to others (customers, attackers)
- We need to look at:
    - What are the targets
    - What are the threats
- Example
    - State wants all government devices with full disk encryption
    - Risks / Costs
        - Not all devices supported at the time
        - Devices without sensitive information don't need it
            - Thinclient or Test machines that are flashed often

### Why Adversarial Thinking?
- Cyber offense is always easier than cyber defense
    - Defense must always be right
    - Defense cannot make a mistake
    - Defense often does not know the goals and tactics of the offense
- We need to think like the adversaries
- You do not need to know how they gain access, nor do you need to be a cyber expert
- If you approach your solutions asking, “What if they had access?” then
    - small design changes could make it harder for a cyber enabled adversary to be successful


### Who is the Adversary?
- Skill
    - Script kiddies
    - Amateur
    - Professional
- Tools
    - Computers/software
    - Social engineering
    - Physical access


### How are they organized
- Individual
- Cause based
- Loose collective
- As a business
- State based
    - State supported
    - State sponsored
    - State run


### Attributes
- Creative
- Patient
- Persuasive
- Opportunistic
- Determined
- Cooperative


### Adversaries' Objectives
- Interrelated and tied to money

- **Monetary Theft**
    - Redirection/diversion
    - Account access
    - Purchasing
    - Fraud
    - Ransomware 
- **Data**
    - Types
        - Personal Identifiable Information (PII)
        - Records, operational and infrastructure plans
    - Threats
        - Disclosure
        - Reuse (ID theft, bank accounts)
        - First step to another attack
    - Holding it hostage for money
- **Disruption**
    - Physical services
        - Power, traffic, water, gas
        - Building control, medical
    - IT services
        - 911, communications
        - Medical, government services
    - Holding it hostage for money
- **Terror**
    - Personal attacks (often demanding money)
        - Swatting
        - Fake messages: kidnapping, family arrested
        - Direct threats
    - Public attacks to cause fear
        - Disruption / ransom = Terror. 
        - The sole purpose is not to gain money but to cause chaos and fear
- **Public Trust**
    - Attackers may have the goal of eroding trust in:
        - The government
        - Elected officials
        - Certain services
        - Elections
        - Company brands



## What they target

### Vulnerabilities
- Attack Vectors
    - Vulnerable people
    - Vulnerable systems
    - Insider using system
- Information
    - Process
    - People
    - Systems


### Vulnerable Systems
- Attackers pick on vulnerable systems
- Bad access control
- Misconfigured  systems
    - Initial configuration problems
    - Reconfiguration problems
    - Not installing patches or old systems
    - Zero Day


### Type of Systems
- Data systems: contain critical data
    - IP, credit card numbers, etc.
    - Over 37 billion records lost in 2021
- Processes systems: needed for operation
    - Often part of ransomware attack
    - Billing, inventory, medical
- Control systems: control physical assets
    - Pipelines, power grid, traffic control


### Vulnerable People
- People are the weakest link. 
    - Over 50% of data loss is from social engineering, a tactic used by attackers directly against people.
- Malware (ransomware)
    - Phishing and spear-phishing
    - Email attachments
    - Websites 
        - Drive by
        - Directed 
    - Poor access policies

### Attackers use people on the inside
- Intentional
    - Think of the number of egress points and the number of protocols involved
- Accidental
    - As applications become more integrated and seamless, it becomes easier to send data
- Intentionally accidental
    - As we harden our defenses, the attackers are using more social engineering-based attacked to get users to leak information


### Vulnerable Processes 
- Internal processes
    - Gather information 
    - Use knowledge of processes against you
- External processes
    - Supply chain security
    - Partner security

### Attack Triangle
- Vulnerabilities are interrelated

![attack_triangle](./triangle.png)


## How They Do It

### Attacks of Opportunity
- Often carried out by script kiddies
- Pick on vulnerable systems, not installing patches
- Misconfigured  systems
    - Initial configuration problems
    - Reconfiguration problems
- General social engineering


### Advanced Persistent Threat
- Attackers will pick their target(s) and wait until you make a mistake
    - Misconfiguration
    - Not patching a system
- Or they will target your employees with phishing emails
    - Get them to disclose passwords
    - Go to websites to get malware
    - Send attachments with malware 

- The  Internet of  things
    - Power, Water, transportation,  etc.
- Where the money is
    - Banks, people, organizations (lower tech = target)
- Intellectual  property
    - Technology (ag sector, manufacturing,  etc)
- Gain  access


### People on the Inside
- Often easier to get someone on the inside to:
    - Install malware
    - Transfer money
    - Expose data
    - Allow access


## Malware

### How does it work

- Computer code that needs privileged access
- It needs to be able to restore the data
- Needs to be hard for user to restore
- It uses internal trust to spread

### Targets

- The more reliant they are on computers the more likely they are to pay
- Computers are unusable and they cannot function
- Data is stolen and held for ransom


### How do you get it
- Email
- Phone calls
- Website
- File Sharing
- Computer Breach
- Internal trust
- Trust (my idea)
    - packages in code
    - Supply chain


### Cryptocurrency (Gross)
- Primary driver for the explosion of ransomware attacks
- Very hard to trace
- No government oversight or reporting
- Easy to move money around


## Ransomware

### The Numbers
590 Million first 6 months 2021
400 Million in 2020
68 variants
5.2 Billion since 2011

### Example
- Data Systems
- Process Systems
- Control Systems


### Data Systems
- New ransomware – Encrypt and Steal
    - Threaten release of data
    - Police info, PII, etc
- Target
    - Malware (through supplier), Theft of CC #
- Sony:
    - Malware, APT, Disruption, Revenge



### Target 2014
- Attackers had malware that reads memory and sends it to a drop site
- Unclear if they picked certain retailers or just looked for ones they could insert the malware
- Used weak security at HVAC company to get login name and password to Target
- Tested software Nov 15-28
- Nov 30 pushed to most POS terminals


### Sony 2014
- Still unclear on how they gained access
- Appears to be APT
- Attackers raised the stakes in that this is one of the first attacks that caused widespread destruction of computing resources
- Well written and very complex malware


### Process Systems
- Hospitals:
    - Ransomware,  shutdown  medical  systems
- Colonial  pipeline
    - Ransomware,  effected  business  processes
- JBS
    - Ransomware,  effected  business  processes
- Co-op
    - Effected  grain handling and farmer data


### Control Systems
- Florida water plant
    - Remote access, added toxic chemical
- Ukraine power grid
    - Russian attack 
- Iranian centrifuges
    - Changed sensor data


### Turkish pipeline
- 2008, by some reports one of the earliest cyber physical  attacks
- Gained access via internet cameras
- Changed  settings
- Turned off monitors
- 2015 German Steel Mil



## Defense
- First cyber security is an unfair war
- Defenders must be perfect
- Attackers only need to get it right once.
- Law enforcement often cannot tell if something happened.
- Let's look at where we are at
- Prevention (defense)
- Detection
- Attribution


- Hard to know when are being attacked
- Often we know because of some other data (bank statement, audit, etc.)
- Finding an attack in all of the data
- Users and organizations need to play a role.
- Very little information sharing to know if there is a pattern across  organizations



















