def getArrayOfStringReturnInt(stringArray):
    newArray = stringArray
    for num in range(0, len(stringArray)):
        newArray[num] = int(stringArray[num])
    return newArray

def getLinesReturnArray(lines):
    xy = []
    for grids in gridLines:
        array = grids.replace('\\n', '').split()
        xy.append(getArrayOfStringReturnInt(array))
    return xy

def biggestUpDown(xy):
    xxxx = 0
    for numX in range(0, len(xy[0])):
        for numY in range(0, len(xy)-3):
            if xxxx is 0:
                xxxx = xy[numY][numX] * xy[numY+1][numX] * xy[numY+2][numX] * xy[numY+3][numX]
            all = xy[numY][numX] * xy[numY+1][numX] * xy[numY+2][numX] * xy[numY+3][numX]
            if xxxx < all:
                xxxx = all
    return xxxx

def biggestLeftRight(xy):
    xxxx = 0
    for numY in range(0, len(xy)):
        for numX in range(0, len(xy[0])-3):
            if xxxx is 0:
                xxxx = xy[numY][numX] * xy[numY][numX+1] * xy[numY][numX+2] * xy[numY][numX+3]
            all = xy[numY][numX] * xy[numY][numX+1] * xy[numY][numX+2] * xy[numY][numX+3]
            if xxxx < all:
                xxxx = all
    return xxxx

def biggestDiagonally(xy):
    return None

grid = open('grid.txt', 'rt')
gridLines = grid.readlines()
grid.close()
xy = getLinesReturnArray(gridLines)
print(biggestUpDown(xy))
print(biggestLeftRight(xy))