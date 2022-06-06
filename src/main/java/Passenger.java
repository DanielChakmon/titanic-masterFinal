public class Passenger {
    private String passengerId;
    private String survived;
    private String pClass;
    private String name;
    private String sex;
    private String age;
    private String sibSp;
    private String parCh;
    private String ticket;
    private String fare;
    private String cabin;
    private String embarked;

    public Passenger(String passengerId, String survived, String pClass, String name, String sex, String age, String sibSp, String parCh, String ticket, String fare, String cabin, String embarked) {
        this.passengerId = passengerId;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parCh = parCh;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    public Passenger() {
        this.passengerId = "";
        this.survived = "";
        this.pClass = "";
        this.name = "";
        this.sex = "";
        this.age = "";
        this.sibSp = "";
        this.parCh = "";
        this.ticket = "";
        this.fare = "";
        this.cabin = "";
        this.embarked = "";
    }

    public int getPassengerId() {
        int ans = -1;
        if (!passengerId.equals("")) {
            ans = Integer.parseInt(passengerId);
        }
        return ans;
    }

    public int getSurvived() {
        int ans = -1;
        if (!survived.equals("")) {
            ans = Integer.parseInt(survived);
        }
        return ans;
    }

    public int getPClass() {
        int ans = -1;
        if (!pClass.equals("")) {
            ans = Integer.parseInt(pClass);
        }
        return ans;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        int ans = -1;
        if (!age.equals("")) {
            ans =(int) Double.parseDouble(age);
        }
        return ans;
    }

    public int getSibSp() {
        int ans = -1;
        if (!sibSp.equals("")) {
            ans = Integer.parseInt(sibSp);
        }
        return ans;
    }

    public int getParCh() {
        int ans = -1;
        if (!parCh.equals("")) {
            ans = Integer.parseInt(parCh);
        }
        return ans;
    }

    public String getTicket() {
        return ticket;
    }

    public float getFare() {
        float ans = -1;
        if (!fare.equals("")) {
            ans = Float.parseFloat(fare);
        }
        return ans;
    }

    public String getCabin() {
        return cabin;
    }

    public char getEmbarked() {
        char ans = 'A';
        if (!embarked.equals("")) {
            ans = embarked.charAt(0);
        }
        return ans;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public void setSurvived(String survived) {
        this.survived = survived;
    }

    public void setPClass(String pClass) {
        this.pClass = pClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSibSp(String sibSp) {
        this.sibSp = sibSp;
    }

    public void setParCh(String parCh) {
        this.parCh = parCh;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }

    public String getFormattedName() {
        String ans = new String();
        if (name != null) {
            String[] partsOfName = name.split(",");
            String[] titleAndFirstName = partsOfName[1].split("\\.");
            ans = titleAndFirstName[1] + " " + partsOfName[0];
        }
        return ans;
    }

    public boolean doesPassengerMatchParameters(FilterParameters filterParameters) {
        boolean ans = true;
        if ((filterParameters.getMaxPassengerId() != -1 && filterParameters.getMaxPassengerId() < getPassengerId()) ||
                (filterParameters.getMinPassengerId() != -1 && filterParameters.getMinPassengerId() > getPassengerId()) ||
                ((filterParameters.getPClass() != -1) && (filterParameters.getPClass() != getPClass())) ||
                (!filterParameters.getNameContains().equals("") && !getFormattedName().contains(filterParameters.getNameContains())) ||
                (!filterParameters.getGender().equals("All") && !filterParameters.getGender().equals(sex)) ||
                ((filterParameters.getSibSp() != -1) && (filterParameters.getSibSp() != getSibSp())) ||
                ((filterParameters.getParCh() != -1) && (filterParameters.getParCh() != getParCh())) ||
                (!(filterParameters.getTicketNumber().equals("")) && (!filterParameters.getTicketNumber().equals(ticket))) ||
                (filterParameters.getMaxFare() != -1 && filterParameters.getMaxFare() < getFare()) ||
                (filterParameters.getMinFare() != -1 && filterParameters.getMinFare() > getFare()) ||
                ((!filterParameters.getCabin().equals("")) && (!filterParameters.getCabin().equals(getCabin()))) ||
                ((filterParameters.getEmbarked() != 'A') && (filterParameters.getEmbarked() != getEmbarked())) ||
                ((filterParameters.getSurvivalStatus() != -1) && (filterParameters.getSurvivalStatus() != getSurvived()))) {
            ans = false;
        }

        return ans;
    }
    public String toString() {
        return passengerId + "," + survived + "," + pClass + "," + getFormattedName() + "," + sex + "," + age + "," + sibSp + "," + parCh + "," + ticket + "," + fare + "," + cabin + "," + embarked;
    }
}

