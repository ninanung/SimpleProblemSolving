def SOP():
    stone = 1000000
    array = [2]
    for num in range(3, stone):
        boolen = True
        for find in range(2, num-1):
            if num % find is 0:
                boolen = False
                break
        if boolen:
            array.append(num)
            print(array[-1])
    print(array)

def CPN(n):
    array = []
    for num in range(1, n+1):
        if n % num is 0:
            array.append(num)
    print(array)

SOP()