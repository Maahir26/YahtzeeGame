public class Dice {
   private int value;
  
   //values initialized to -1
   public Dice() {
       value = -1;
   }
  //initialized value to given argument
   public Dice(int value) {
       this.value = value;
   }
  //generates a random number from 1 to 6
   public void roll() {
       RandomNumber R = new RandomNumber();
       value = R.getRandomNumber(1, 6);
   }
  //returns the dice value
   public int getValue() {
       return value;
   }
}