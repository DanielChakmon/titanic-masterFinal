public class FilterParameters {
    private int minPassengerId;
    private int maxPassengerId;
    private int pClass;
    private String nameContains;
    private String gender;
    private int sibSp;//siblings&spouses count
    private int parCh;//parents&children count
    private String ticketNumber;
    private int minFare;
    private int maxFare;
    private String cabin;
    private char embarked;
    private int survivalStatus;


    public FilterParameters(int minPassengerId, int maxPassengerId, int pClass, String nameContains, String gender, int sibSp, int parCh, String ticketNumber, int minFare, int maxFare, String cabin, char embarked, int survivalStatus) {
        this.minPassengerId = minPassengerId;
        this.maxPassengerId = maxPassengerId;
        this.pClass = pClass;
        this.nameContains = nameContains;
        this.gender = gender;
        this.sibSp = sibSp;
        this.parCh = parCh;
        this.ticketNumber = ticketNumber;
        this.minFare = minFare;
        this.maxFare = maxFare;
        this.cabin = cabin;
        this.embarked = embarked;
        this.survivalStatus=survivalStatus;
    }

    public FilterParameters() {
        final int ALL=-1;
        this.minPassengerId = ALL;
        this.maxPassengerId = ALL;
        this.pClass = ALL;
        this.nameContains = new String();
        this.gender = "All";
        this.sibSp = ALL;
        this.parCh = ALL;
        this.ticketNumber = new String();
        this.minFare = ALL;
        this.maxFare = ALL;
        this.cabin = new String();
        this.embarked = 'A';
        this.survivalStatus=ALL;
    }

    public void setMinPassengerId(int minPassengerId) {
        this.minPassengerId = minPassengerId;
    }

    public void setMaxPassengerId(int maxPassengerId) {
        this.maxPassengerId = maxPassengerId;
    }

    public void setPClass(int pClass) {
        this.pClass = pClass;
    }

    public void setNameContains(String nameContains) {
        this.nameContains = nameContains;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSibSp(int sibSp) {
        this.sibSp = sibSp;
    }

    public void setParCh(int parCh) {
        this.parCh = parCh;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setMinFare(int minFare) {
        this.minFare = minFare;
    }

    public void setMaxFare(int maxFare) {
        this.maxFare = maxFare;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setEmbarked(char embarked) {
        this.embarked = embarked;
    }

    public void setSurvivalStatus(int survivalStatus) {
        this.survivalStatus = survivalStatus;
    }

    public int getMinPassengerId() {
        return minPassengerId;
    }

    public int getMaxPassengerId() {
        return maxPassengerId;
    }

    public int getPClass() {
        return pClass;
    }

    public String getNameContains() {
        return nameContains;
    }

    public String getGender() {
        return gender;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParCh() {
        return parCh;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public int getMinFare() {
        return minFare;
    }

    public int getMaxFare() {
        return maxFare;
    }

    public String getCabin() {
        return cabin;
    }

    public char getEmbarked() {
        return embarked;
    }

    public int getSurvivalStatus() {
        return survivalStatus;
    }
}
