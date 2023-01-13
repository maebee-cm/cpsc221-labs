import java.io.File;
import java.util.Scanner;

public class FormatChecker {
    public static void main(String[] args) {
        // Check if we were given any arguments, if not print how to use the program and exit
        if (args.length == 0) {
            System.out.println("Program usage: java FormatChecker [file1 file2 ... fileN]");
            return;
        }

        // Loop through each file we were given and see if it has errors
        for(String filePath: args) {
            System.out.println(filePath);

            try {
                Scanner scanner = new Scanner(new File(filePath));

                // Check that the first line specifies matrix size correctly, with only two numbers
                String matrixSizeLine = scanner.nextLine();
                String[] matrixSizeSplit = matrixSizeLine.split("\\s+");
                if (matrixSizeSplit.length != 2) {
                    throw new MatrixSizeFormatException("line contains more than two items: " + matrixSizeLine);
                }

                // Parse those numbers, this will throw an exception if the numbers are incorrectly formatted.
                int rows, columns;
                rows = Integer.parseInt(matrixSizeSplit[0]);
                columns = Integer.parseInt(matrixSizeSplit[1]);

                // Loop through each row to check its data
                for (int i = 0; i < rows; i++) {
                    //Check that the row exists
                    if (!scanner.hasNextLine()) {
                        throw new MatrixFormatException("Matrix was specified as having " + rows + " rows, but only " +
                                (i-1) + " were found");
                    }

                    //Check that the row has enough numbers in it
                    String[] rowData = scanner.nextLine().split("\\s+");
                    if (rowData.length != columns) {
                        throw new MatrixFormatException("incorrect number of columns given, expected " + columns +
                                " items but found " + rowData.length);
                    }

                    for (int j = 0; j < columns; j++) {
                        // Make sure the numbers in each column parse correctly, will throw if not
                        Double.parseDouble(rowData[j]);
                    }
                }
                // Check if there are still more rows after we're done
                if (scanner.hasNext()) {
                    throw new MatrixFormatException("Matrix was specified as having " + rows + " rows, but more were found");
                }

                // File checks out
                System.out.println("VALID\n");
            } catch (Exception e) {
                // File has problems
                System.out.println(e);
                System.out.println("INVALID\n");
            }
        }
    }
}
