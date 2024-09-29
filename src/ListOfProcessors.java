import java.util.Arrays;
import java.util.List;

public class ListOfProcessors {
    private List<List<Integer>> list;

    public ListOfProcessors(){
        list = Arrays.asList(
                    Arrays.asList(1, 2, 3, 4, 0),
                    Arrays.asList(1, 2, 3, 4),
                    Arrays.asList(2, 3, 4, 0),
                    Arrays.asList(1, 3, 4, 0),
                    Arrays.asList(1, 2, 4, 0),
                    Arrays.asList(1, 2, 3, 0),
                    Arrays.asList(1, 2, 3),
                    Arrays.asList(1, 2, 4),
                    Arrays.asList(1, 2, 0),
                    Arrays.asList(1, 3, 4),
                    Arrays.asList(1, 3, 0),
                    Arrays.asList(2, 3, 0),
                    Arrays.asList(2, 3, 0),
                    Arrays.asList(1, 4, 0),
                    Arrays.asList(3, 4, 0),
                    Arrays.asList(1, 2),
                    Arrays.asList(1, 3),
                    Arrays.asList(1, 4),
                    Arrays.asList(1, 0),
                    Arrays.asList(2, 3),
                    Arrays.asList(2, 4),
                    Arrays.asList(2, 0),
                    Arrays.asList(3, 4),
                    Arrays.asList(3, 0),
                    Arrays.asList(4, 0),
                    Arrays.asList(1),
                    Arrays.asList(2),
                    Arrays.asList(3),
                    Arrays.asList(4)
        );
    }

    public List<List<Integer>> getList() {
        return list;
    }
}
