import java.util.ArrayList;

public class ArrayList2
{
	public static void main(String[] args){
		System.out.println("Question 1: " + Sieve(100));
		System.out.print("Question 2: ");
		Goldbach(16);
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		for(int z = 0; z < 10; z++){
			list1.add(6);
		}
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		for(int r = 0; r < 5; r++){
			list2.add(9);
		}
		System.out.println("Question 3: " + BigInt(list1, list2));
	}
	
	public static ArrayList<Integer> Sieve(int n){ //n will be greater than 2
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(n < 2) return ret;		
		for(int i = 2; i <= n; i++){
			ret.add(i);
		}
		int index = 0;
		int p = ret.get(index);
		while(true){
			for(int x = index; x < ret.size(); x++){
				if(ret.get(x) % p == 0 && ret.get(x) > p){
					ret.remove(x);
					x--;
				}
			}
			if(index + 1 < ret.size()){
				index++;
				p = ret.get(index);
			}
			else break;
		}
		return ret;
	}
	
	public static void Goldbach(int n){
		if(n % 2 != 0){
			System.out.println("Cannot compute " + n + ", it is an odd number.");
			return;		
		}
		ArrayList<Integer> primes = Sieve(n);
		for(int i = primes.size() - 1; i >= 0; i--){
			if(primes.get(primes.size()-1) + primes.get(i) == n){
				System.out.println(primes.get(primes.size()-1) + " + " + primes.get(i) + " = " + n);
				break;
			}
		}
	}
	
	public static ArrayList<Integer> BigInt(ArrayList<Integer> A, ArrayList<Integer> B){
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		if(a.size() >= b.size()){
			a = A;
			b = B;
		}
		else if(a.size() < b.size()){
			a = B;
			b = A;
		}
		ArrayList<Integer> carry = new ArrayList<Integer>();
		for(int i = 0; i <= a.size(); i++){					 //to carry numbers over 9
			carry.add(0);
		}
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int y = 0; y < a.size(); y++){
			ret.add(0);
		}
		int colSum = 0;
		for(int x = 0; b.size()-1-x >= 0; x++){
			colSum = a.get(a.size()-1-x) + b.get(b.size()-1-x) + carry.get(carry.size()-1-x); //total sum of this column
			if(colSum >= 10){
				carry.set(carry.size()-2-x, colSum / 10); //send tens digit to be carried to next column 
				ret.set(ret.size()-1-x, colSum - 10);	  //send ones digit to current column
			}
			else ret.set(ret.size()-1-x, colSum); 
		}
		if(a.size() > b.size()){ //if there is an imbalance in the size of each ArrayList
			for(int index = a.size() - b.size() - 1; index >= 0; index--){
				colSum = carry.get(index+1) + a.get(index);
				if(colSum >= 10){
					carry.set(index, colSum / 10);
					ret.set(index, colSum - 10);
				}
				else ret.set(index, colSum);
			}
			//System.out.println(carry);
		}
		if(carry.get(0) != 0) ret.add(0, carry.get(0));
		return ret;
	}
}