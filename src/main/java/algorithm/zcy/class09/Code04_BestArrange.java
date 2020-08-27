package main.java.algorithm.zcy.class09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有一组会议，每个会议的开始时间结束时间不同，求能开最多几场会议
 * 贪心：会议结束的时间越早，优先级越高
 */
public class Code04_BestArrange {

	/**
	 * 构建一个会议
	 */
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return  end + "";
		}
	}

	public static int bestArrange1(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return process(programs, 0, 0);
	}

	/**
	 * @param programs 还剩什么会议都放在programs里
	 * @param done 之前已经安排了多少会议，数量
	 * @param timeEnd 目前来到的时间点是什么
	 * @return 能安排的最多会议数量
	 */
	public static int process(Program[] programs, int done, int timeEnd) {
		if(programs.length==0){
			return done;
		}
		int max = done;
		for (int i = 0; i < programs.length; i++) {
			if(programs[i].start>=timeEnd){
				Program[] copyOther = copyOther(programs, i);
				//比较每一种可能性
				max=Math.max(max,process(copyOther,done+1,programs[i].end));
			}
		}
		return max;
	}

	public static Program[] copyOther(Program[] programs,int i){
		Program[] programs1 = new Program[programs.length - 1];
		int j=0;
		for (int k = 0; k < programs.length; k++) {
			if(k!=i){
				programs1[j++]=programs[k];
			}
		}
		return programs1;
	}



	public static int bestArrange3(Program[] programs) {
		Arrays.sort(programs, new ProgramComparator());
		int timeLine = 0;
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (timeLine <= programs[i].start) {
				result++;
				timeLine = programs[i].end;
			}
		}
		return result;
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}
	public static int bestArrange2(Program[] programs) {
		final PriorityQueue<Program> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
		Arrays.stream(programs).forEach(program -> queue.offer(program));
		int result = 0;
		int timeEnd = 0;
		while (!queue.isEmpty()){
			Program poll = queue.poll();
			if(timeEnd<=poll.start){
				result++;
				timeEnd=poll.end;
			}
		}

		return result;
	}


	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != bestArrange2(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
