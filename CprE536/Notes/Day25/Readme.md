
# Steganography


### Intro video
- Car theft via relay box
- Unlocks car
- Hotwire to start car
- Smart key fobs have passive frequencies that are caught
- Gussing can generate unlock and lock from that?

### Stego
- Covered writing
- Secret comms that conceal existence of message
- Crypto is to prevent reading (still know it exists)
- Stego is to prevent knowledge of message
- Watermarking is also embedding in information
    - not necessarily hidden



### Process
- Cover media
- Encryption Key (Optional)
- Info to Hide / Payload / Message 
- Embedding algorith takes the above 
- Encrypts and embeds payload in cover media
- Generates marked media

- Extract by stego media
- Inverse embed and decrypt with shared key
- Gets mesage

### Intention
- Transmit secret message in innocent-looking cover medium so it is undetectable
- Robustness not issue
- Capacity is desired for large message
- Always invisible
- Dependent on file format

### Where to hide
- Images
- Audio
- Text
- Disk space
- Hidden partition
- Netowrk packets
- Software
- Circuitry

### Issues
- Perceptability
    - Does it distort medium?
- Capacity
    - How much can be hidden?
- Robustness
    - Can it survive manipulation?
- Trade-off
    - More Robustness for Lower Capacity
    - Lower perceptivility for Lower Capacity

### Techniques
- Substitution
    - Bit plane
    - Palette-based
- Signal Processing
    - Transform
    - Spread sceptrum
- Coding
    - Quantizing, dithering
    - ECC
- Statistical Methods
- Cover generation methods


### Bit plane
- Replace LSB of image intesity with message bit
- can be 3 or 4 of LSBs (8 bits per pixel or per color)
- Data is hidden in noise of image
- Fragile to manipulation
- Large storage
    
### Steganalysis
- Detection of hidden data from stego techniques
- Know
    - Stego-image only
    - Original cover and stego image
    - Hidden message
    - Alorithm used to embed data
    - Patterns available from analyzing many stego images
- Approaches
    - Palette 
    - Histogram characerization
    - Stat testing
    - Header information
- Not in literature approaches
    - Neural networks
    - Genetic algorithms
    - Simulated Annealing
    - Other standar signal processing


### Invertibility Problem 





