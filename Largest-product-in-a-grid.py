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
    return None

def biggestLeftRight(xy):
    return None

def biggestDiagonally(xy):
    return None

grid = open('grid.txt', 'rt')
gridLines = grid.readlines()
grid.close()
xy = getLinesReturnArray(gridLines)