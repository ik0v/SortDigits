import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class textFWriter {

    private String fName;
    private RandomAccessFile rAFile;
    private int pointer=0;

    public textFWriter(String fName) throws IOException {
        this.fName = fName;
        rAFile = new RandomAccessFile(fName, "rw");
    }

    public void writeNr(int newNr) throws IOException {
        if(rAFile.length() == 0 || compareTo(newNr) == 0) {
            rAFile.writeInt(newNr);
            pointer++;
        }
        else if(compareTo(newNr) > 0) {
            rAFile.writeInt(newNr);
            pointer++;
        }
        else if(compareTo(newNr) < 0) {
            pointer--;
            rAFile.seek(4*pointer);
            int nrCopy = rAFile.readInt();
            rAFile.seek(4*pointer);
            rAFile.writeInt(newNr);
            pointer++;
            rAFile.seek(4*pointer); //? -- a bit strange, but code didnt work without this line
            rAFile.writeInt(nrCopy);
            pointer++;
        }
    }

    public int compareTo(int newNr) throws IOException {
        pointer--;
        rAFile.seek(4*pointer);
        int checkNr = rAFile.readInt();
        pointer++;
        if(checkNr < newNr) return 1;
        if(checkNr > newNr) return -1;
        else return 0;

    }

    public void printNumbers() throws IOException {
        rAFile.seek(0);
        try {
            while (true) {
                System.out.print(rAFile.readInt() + " ");
            }
        } catch (EOFException e) {
        }
    }

    public int getPointer() {
        return pointer;
    }

    public void closeF() throws IOException {
        rAFile.close();
    }
}
