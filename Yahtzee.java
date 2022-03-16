public class Yahtzee {

	private Dice[] dice;
	//returns the total of dices
	private int totalofalldices() {
		int sum = 0;
		for(int i=0;i<5;i++) {
			sum+=this.dice[i].getValue();
		}
		return sum;
	}
	//Puts the value of all dices into an array
	private int[] getValueArray() {
		int valueArray[] = new int[5];
		for(int i = 0; i<5; i++) {
			valueArray[i]= this.dice[i].getValue();
		}
		return valueArray;
	}
	//sorts the array in ascending pattern*/
	private int[] sortArray(int[] oldArray) {
		int temp = 0;
		int array[] = oldArray;
		for (int i = 0; i <array.length; i++) {     
			for (int j = i+1; j <array.length; j++) {     
				if(array[i] >array[j]) {     
					temp = array[i];    
					array[i] = array[j];    
					array[j] = temp;    
				}     
			}     
		}
		return array;
	}
	//if small straight then returns 30 points otherwise 0 points
	private int SmallStraight() {
        int score = 0;
        int consecutiveCount = 1; 
        int array[] = sortArray(this.getValueArray());
        for(int i = 1; i < 5; i++) { 
            if(array[i] == array[i-1] + 1) {          	
            	consecutiveCount ++;
            }
        }   
        //returns 30 points if numbers are a small straight
        if(consecutiveCount >=4) {     	
            score = 30;
        }     
        return score;
    }
	//checks if the numbers are a large straight
	private int LargeStraight() {
        int score = 0;
        boolean isStraight = true;
        int array[] = sortArray(this.getValueArray()); 
        for(int i = 1; i < 5; i++) {   
        	//checks if the current number is more than the previous number
            if(array[i] != array[i-1] + 1) {            	
            	isStraight = false;
            }
        }
        if(isStraight) {       	
            score = 40;
        }
        return score;
    }			
	public Yahtzee() {
		dice = new Dice[5];		
		for(int i=0; i<5; i++) {
			this.dice[i] = new Dice();
			this.dice[i].roll();
		}
	}		
	public Yahtzee(Dice[] dice) {
		this.dice = dice;
	}
	//counts how many dice show each of the possible values from 1-6
	public int[] getValueCount() {
		int DiceValues[] = new int[]{0, 0, 0, 0, 0, 0};		
		for(int i = 0; i<5; i++) {			
			for(int k = 0; k<6; k++) {				
				if(this.dice[i].getValue() == k+1) {				
					DiceValues[k]++;
				}
			}
		}		
		return DiceValues;
	}
	/*creates an int array with 13 elements, to record all the possible scores for the dice
	in the instance variable*/
	public int[] getScoreOptions() {		
		int ScoreOptions[] = new int[13];		
		for(int i = 0; i<6; i++) {			
			ScoreOptions[i]=this.getValueCount()[i]*(i+1);							
			if(this.getValueCount()[i]>=3) {				
				for(int j=0;j<6;j++) {					
					if(this.getValueCount()[j]==2) {					
						ScoreOptions[8]=25;
					} else {						
						ScoreOptions[6]=this.totalofalldices();
					}
				}
			}			
			if(this.getValueCount()[i]>=4) {				
				ScoreOptions[7]=this.totalofalldices();
			}			
			if(this.getValueCount()[i]==5) {			
				ScoreOptions[11]=50;
			}
		}				
		ScoreOptions[9] = this.SmallStraight();
		ScoreOptions[10]= this.LargeStraight();
		ScoreOptions[12]=this.totalofalldices();		
		return ScoreOptions;
	}	
	//checks to see what gives the max score
	public int[] score() {
		int arr[]=this.getScoreOptions();
		int index = 0;
		int maximum = arr[0];
		for(int i = 0; i<13; i++){
			if(arr[i]>maximum){
				index = i;
				maximum = arr[i];
			}
		}
		int returnArr[] = {arr[index], index};
		return returnArr;
	}
	//checks to see if two classes are the same
	public boolean equals(Yahtzee other) {
		boolean equal = true;
		for(int i = 0; i<6; i++) {		
			if(this.getValueCount()[i] != other.getValueCount()[i]) {
				equal = false;
			}
		}
		return equal;
	}		
	//returns the string of the dice values in a formatted way
	public String toString() {
		String arrD = "Dice: {";
		for(int i=0; i<5; i++) {
			arrD = arrD + String.valueOf(this.dice[i].getValue());
			if(i==4) {
				arrD+="}";
			} else {
				arrD+=", ";
			}
		}
		return arrD;
	}
}