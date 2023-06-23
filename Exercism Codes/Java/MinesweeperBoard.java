import java.util.ArrayList;
import java.util.List;

public class MinesweeperBoard {
	private List<String> inputBoard;
	
	public MinesweeperBoard(List<String> inputBoard) {
		this.inputBoard = inputBoard;
	}
	
	public List<String> withNumbers() {
		List<String> t = new ArrayList<String>(inputBoard);
		for(int i = 0; i < t.size(); i++) {
			for(int j = 0; j < t.get(i).length(); j++) {
				if(t.get(i).charAt(j) == ' ') {
					int startIndex = i - 1 >= 0 ? i - 1 : i;
					int endIndex = i + 1 < t.size() ? i + 1 : i;
					int startIndex2 = j - 1 >= 0 ? j - 1 : j;
					int endIndex2 = j + 1 < t.get(i).length() ? j + 1 : j;
					int number = 0;
					for(int k = startIndex; k <= endIndex; k++) {
						for(int l = startIndex2; l <= endIndex2; l++) {
							if(t.get(k).charAt(l) == '*') number++;
						}
					}
					if(number > 0) {
						StringBuilder sb = new StringBuilder(t.get(i));
						sb.setCharAt(j, (char)(number + '0'));
						t.set(i, sb.toString());
					}
				}
			}
		}
		return t;
	}
}