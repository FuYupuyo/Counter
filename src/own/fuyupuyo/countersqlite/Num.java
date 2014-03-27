package own.fuyupuyo.countersqlite;

public class Num {
	private int num;

	public Num() {
		super();
		num = 0;
	}

	public Num(int num) {
		super();
		this.num = num;
	}

	public int get() {
		return num;
	}

	public void add() {
		num++;
	}

	public void reset() {
		num = 0;
	}

	@Override
	public String toString() {
		return num + "";
	}
}
