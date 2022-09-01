import random
for i in range(10):
    random.seed(i)
    print(random.sample(range(1, 70), 5), end=",\t")
    print("\033[91m {}\033[00m" .format(random.sample(range(1, 27), 1)))
