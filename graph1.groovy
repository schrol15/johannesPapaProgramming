def zeichne(int width, int height, Closure f) {
    assert width > 0: "width must be set and larger than 0"
    assert height > 0: "height must be set and larger than 0"
    def funResult = calculateFResults(width, height, f)
    println funResult
    int halfHeight = height / 2
    for (int row = 0; row < height; row++) {
        //print valueAsString
        if (row == halfHeight) {
            zeichneXAchse(width)
            row++;
        } else {
            zeichneYAchse(width, height, row, funResult)
        }
    }
}

def zeichneXAchse(width) {
    int halfWidth = width / 2
    for (def col = 0; col < width; col++) {
        if (col == halfWidth)
            print "|"
        else {
            if (col % 10 == 0)
                print ","
            else
                print "-"
        }
    }
    println ">"
    for (def col = 0; col < width; col++) {
        int colValue = col - halfWidth
        String colValueAsString = "" + colValue
        if (col % 10 == 0) {
            if (halfWidth == col) {
                print "|"
            } else {
                print colValueAsString
                col = col + colValueAsString.size() - 1
            }
        } else
            print " "
    }
    println ""
}

def zeichneYAchse(width, height, row, Map funResult) {
    int halfWidth = width / 2
    int halfHeight = height / 2
    int rowValue = halfHeight - row
    String rowValueAsString = "" + rowValue
    rowValueAsString = rowValueAsString.padRight(3)
    boolean pointSet = false
    for (def col = 0; col < width; col++) {
        int colValue = col - width/2
        if (funResult[colValue] == rowValue) {
            print "*"
        } else if (col == halfWidth) {
            print "|"
            if (row % 5 == 0) {
                print rowValueAsString
                col = col + rowValueAsString.size()
            }

        } else
            print " "
    }
    println ""
}

def f = (x) -> {
    return (0.7)*(x -10)
}

def Map<Integer, Integer> calculateFResults(int width, int height, Closure f) {
    def result = [:]
    int halfWidth = width / 2
    for (col = 0; col < width; col++) {
        int x = col - halfWidth
        int y = f(x)
        result[x] = y
    }
    return result
}

zeichne(100, 40, f)

// 0) Berechne Funktionswerte für Funktion f(x). Resultat-Typ: Map<Integer,Integer>
//1) zeichne: einfacher Strich mit als Parameter gegebener Breite (Anzahl Zeichen)
//2) zeichne: einfacher Strich mit Mittelmarker |
//3) zeichne: einfacher Strich mit Marker all 10 chars
//4) zeichne: füge hinzu y-Achse mit gegebener Höhe (Anzahl Zeilen)
