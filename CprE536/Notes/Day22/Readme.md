
# Cryptocurrency

### Intro Idea
- Addressing
    - Tying coin address to person
    - Blockchain itself tracks the path of where coins are sent
    - To determine ownership
- Ransomware

### Bitcoin P2P network
- Ad-hoc network with random topology
- Port 8333
- All nodes equal (except ...)
    - Exchanges to convert to/from real money
        - Or managers for wallet systems 
    - Mining systems
    - Longer running more important
    - More neighbors than others as well
- New nodes join at any time
- Forget nodes after 3 hours


### Transactions
- Broadcast
- Digitally signed by payer
- Says paying to pubkey of payee
- Confirmed onced mined and put into blockchain by miner
- Losing key means losing control of your money
- Inputs  
    - Coinbase means mining reward
    - Otherwise it is what is being sent and from who
    - Can have multiple (value, address)
- Outputs
    - How much is sent to who
    - Can split values from input
    - Sums just need to match


### Creating block 
- find nonce st
    - H(nonce || prev_hash || tx1 || tx2 ... )
    - Leading number of zeroes in hash
        - Getting harder over time 
    - Have to just repeat and try to find one 
        - Hard / Slow
    - Easy to verify
- First to mine it 
    - Gets the reward
    - Will distribute out in p2p network
    - Majority wins
- Create mining pools 
    - Splitting reward
    - Distributed computing


### Attacks
- Double spending
    - Prevent multiple uses of same coin
    - Check if payer and payee show up multiple times
    - Keep 1
- If more than 50%
    - Steal coins
    - Make fake transactions
    - Supress transactions 
    - Set block reward
    - Not realistic 


### Online Wallets and Exchange
- On phone or online service
    - Store keys for transactions
- Exchanges 
    - Like banks
    - Transfer bitcoin to currency
    - Buy and sell
        - match buyer of BTC to seller
    - make and reciever payments

### Address clustering
- Temporary address for same person 
- Or for mining pools where it splits rewards among people
- Learn that these addresses are together
    - Learn miners and payee/payers 

- Heuristics
    - Inputs are all the same person sending money (digital signatures remember)
    - Coinbases show miners and pools
        - Addresses together
    - Outputs can be used as payees only once
        - One time change address for the purpose of change
        - Ensure only show once in output
    - Change is paid to the payer
        - Cluster with the payers input addresses


### Hiding
- Send through many sequences of transactions 
- Make small to go unnoticed
- Mixer
    - Takes transactions 
    - Mix them together 
    - Send that as transaction
    - Hide exactly where payments are coming from
    - Generate smaller transactions




