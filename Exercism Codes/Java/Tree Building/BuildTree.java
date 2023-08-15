import java.util.ArrayList;
import java.util.List;

class BuildTree {

    int count = 1;

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        if(records.isEmpty()) return null;
        records.sort((a, b) -> a.getRecordId() - b.getRecordId());
        if(records.stream().noneMatch(r -> r.getParentId() == 0 && r.getRecordId() == 0) || records.get(records.size() - 1).getRecordId() != records.size() - 1)
            throw new InvalidRecordsException("Invalid Records");
        TreeNode tree = build(records, 0);
        if(count != records.size()) throw new InvalidRecordsException("Invalid Records");
        return tree;
    }

    TreeNode build(ArrayList<Record> records, int node) {
        TreeNode tree = new TreeNode(node);
        List<Record> childCount = records.stream().filter(r -> r.getParentId() == node && r.getRecordId() != node).toList();
        if(childCount.size() > 0) {
            for(Record child : childCount) {
                count++;
                tree.getChildren().add(build(records, child.getRecordId()));
            }
        }
        return tree;
    }

}
