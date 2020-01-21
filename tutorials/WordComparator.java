class WordComparator {
    public static void main(String[] args){
        String firstName = args[0];
        String secondName = args[1];
        System.out.println("Words given: " + firstName + ", " + secondName);
        boolean isSame = firstName.equals(secondName);
        if(isSame){
            System.out.println("They are the same: true");
        }
        else if(!isSame){
            System.out.println("They are the same: false");
        }
    }
}