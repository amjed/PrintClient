# PrintClient
A Java bluetooth printer client, use in a J2ME nokia symbian app

#Usage

static String PRINTSIZE = "B";
.
.

printerUrl = "btspp://" + this.printerstatus + ":1;authenticate=false;encrypt=false;master=false";
PrintClient pc = new PrintClient(printerUrl, PRINTSIZE);
pc.printReceipt(getForm_winning_result_stringItem().getText());