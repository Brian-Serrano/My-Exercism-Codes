import java.util.ArrayList;
import java.util.List;

class Flattener {
    public List<Object> flatten(List<Object> list){
        List<Object> result = new ArrayList<Object>();
        for (Object element : list) {
            if(element == null) continue;
            if (element instanceof List) {
                result.addAll(flatten((List<Object>) element));
            } else {
                result.add(element);
            }
        }
        return result;
    }
}