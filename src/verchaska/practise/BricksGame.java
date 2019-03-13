package verchaska.practise;

import java.io.*;

public class BricksGame {
	public static void main(String args[]) throws IOException {
		new BricksGame().setData();
		
	}

	void setData() throws IOException {
		BufferedReader br = null;
		int sizeOfBigBricks = 0;
		int noOfBigBricks = 0;
		int sizeOfSmallBricks = 0;
		int noOfSmallBricks = 0;
		int sizeOfGoal = 0;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter size of big brick");
			sizeOfBigBricks = Integer.parseInt(br.readLine());
			System.out.println("Enter no of big bricks");
			noOfBigBricks = Integer.parseInt(br.readLine());
			System.out.println("Enter size of small brick");
			sizeOfSmallBricks = Integer.parseInt(br.readLine());
			System.out.println("enter no of small bricks");
			noOfSmallBricks = Integer.parseInt(br.readLine());
			System.out.println("enter size of goal");
			sizeOfGoal = Integer.parseInt(br.readLine());
			if(sizeOfBigBricks<sizeOfSmallBricks)
				throw new NumberFormatException(); 

			if (sizeOfBigBricks < 0 || noOfBigBricks < 0 || sizeOfSmallBricks < 0 || noOfSmallBricks < 0
					|| sizeOfGoal < 0)
				throw new NumberFormatException();
		
			BrickData b=new BrickData();

			b.setNoOfBigBricks(noOfBigBricks);
			b.setSizeOfBigBricks(sizeOfBigBricks);
			b.setSizeOfSmallBricks(sizeOfSmallBricks);
			b.setNoOfSmallBricks(noOfSmallBricks);
			b.setSizeOfGoal(sizeOfGoal);
			
			new BricksGame().isPossibleToReachGoal();
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid data");
			setData();
		} finally {
			br.close();
		}
	}

	void isPossibleToReachGoal() {
		BrickData b=new BrickData();
		int noOfBigBricks = b.getNoOfBigBricks();
		int sizeOfBigBricks = b.getSizeOfBigBricks();
		int sizeOfSmallBricks = b.getSizeOfSmallBricks();
		int noOfSmallBricks = b.getNoOfSmallBricks();
		int sizeOfGoal = b.getSizeOfGoal();
		
		System.out.println(sizeOfBigBricks+""+sizeOfGoal);
		
		
		
		int requiredBigBricks=sizeOfGoal/sizeOfBigBricks;
		//int requiredSmallBricks=sizeOfGoal/sizeOfSmallBricks;

		if (sizeOfGoal % sizeOfSmallBricks == 0) {
			if (areEnoughSmallBricks(sizeOfGoal, sizeOfSmallBricks, noOfSmallBricks))
				System.out.println("true");
		} else if (sizeOfGoal % sizeOfBigBricks == 0) {
			if (areEnoughBigBricks(sizeOfGoal, sizeOfBigBricks, noOfBigBricks))
				System.out.println("true");
		} else {
				if(requiredBigBricks<=noOfBigBricks)
				{
					sizeOfGoal-=requiredBigBricks*sizeOfBigBricks;
				}
				else
				{
					sizeOfGoal-=noOfBigBricks*sizeOfBigBricks;
				}
				
				if(sizeOfGoal%sizeOfSmallBricks==0)
				{
					if(areEnoughSmallBricks(sizeOfGoal, sizeOfSmallBricks, noOfSmallBricks))
						System.out.println("true");
					else
						System.out.println("false");
				}else
				{
					System.out.println("false");
				}
		}

	}

	boolean areEnoughSmallBricks(int sizeOfGoal, int sizeOfSmallBricks, int noOfSmallBricks) {
		return ((sizeOfGoal / sizeOfSmallBricks) <= noOfSmallBricks);
	}

	boolean areEnoughBigBricks(int sizeOfGoal, int sizeOfBigBricks, int noOfBigBricks) {
		return ((sizeOfGoal / sizeOfBigBricks) <= noOfBigBricks);
	}

}
