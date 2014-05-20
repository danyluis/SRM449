package topCoder;

public class Runner {

	public static void main(String[] args) {
//		int[] start = {1};
//		int[] end = {7};
		
//		int[] start = {0, 3, 4};
//		int[] end = {5, 9, 6};

		int[] start = {1,4,5,6,-10};
		int[] end = {101,102,101,100,99};
		
//		int[] start = {-5,-3};
//		int[] end = {-2,-2};
		
		MountainRoad mr = new MountainRoad();
		double len = mr.findDistance(start, end);
		System.out.println("Length: " + len);
		
		len = mr.findDistanceLikeABoss(start,end);
		System.out.println("Length computed like a boss: " + len);
	}

}
