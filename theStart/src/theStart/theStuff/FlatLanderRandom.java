package theStart.theStuff;

import java.util.Random;

public class FlatLanderRandom extends Random {

	private long seed;
	private int x;
	private int y;
	private int xUpper;
	private int yUpper;
	private int count;
	private int seeds;

	public FlatLanderRandom(long seed) {
		setSeed(seed);
	}

	public void setSeed(long seed) {
		this.seed = seed;
		this.seeds = (int) seed;
	}

	public synchronized int next(int bits) {
		seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		return (int) (seed >>> (48 - bits));
	}

	public synchronized double nextDouble(int x, int y, int min, int max) {

		this.seed = this.seed + ((x & 0xFFFF) << 16 | (y & 0xFFFF));

		double rnd = (double) rnd();
		double d = (rnd / (double) (0x7FFFFFFF)) * (max - min) + min;
		return d;
	}
	

	public synchronized double nextDouble(int min, int max) {
		double rnd = (double) rnd();
		double d = (rnd / (double) (0x7FFFFFFF)) * (max - min) + min;
		return d;
	}
	
	
	

	public double nextDouble(int x, int y, long time, int min, int max) {
		this.seeds += seeds * (int) (((x & 0xFFFF) << 16 | (y & 0xFFFF)) | time);

		double rnd = (double) rnd2();
		double d = (rnd / (double) (0x7FFFFFFF)) * (max - min) + min;
		return d;
	}

	public double nextDouble(int x, long time, int min, int max) {
		this.seeds = (int) ((x & 0xFFFF) << 16 | time);

		double rnd = (double) rnd2();
		double d = (rnd / (double) (0x7FFFFFFF)) * (max - min) + min;
		return d;
	}

	public long rnd() {
		seed += 0xe120fc15;
		long tmp;
		tmp = ((long) seed * 0x4a39b70d);
		long m1 = (tmp >> 32) ^ tmp;
		tmp = ((long) m1 * 0x12fad5c9);
		long m2 = (tmp >> 32) ^ tmp;
		return (int) m2;
	}

	public long rnd2() {
		seeds += 0xe120fc15;
		long tmp;
		tmp = ((long) seeds * 0x4a39b70d);
		long m1 = (tmp >> 32) ^ tmp;
		tmp = ((long) m1 * 0x12fad5c9);
		long m2 = (tmp >> 32) ^ tmp;
		return (int) m2;
	}

	public double nextDouble(int x, int y, Integer time, double min, double max) {
		this.seeds += (int) (((x & 0xFFFF) << 16 | (y & 0xFFFF)) | time);

		double rnd = (double) rnd2();
		double d = (rnd / (double) (0x7FFFFFFF)) * (max - min) + min;
		return d;
	}

}
