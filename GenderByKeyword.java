public class GenderByKeyword extends DataAnalyzer {

    private MyHashTable<String, MyHashTable<String, Integer>> data;

    public GenderByKeyword(Parser p) {
        super(p);
    }

    @Override
    public MyHashTable<String, Integer> getDistByKeyword(String rawKeyword) {
        // ADD YOUR CODE BELOW THIS
        String keyword = rawKeyword.strip().toLowerCase();
        return this.data.get(keyword);
        // ADD YOUR CODE ABOVE THIS
    }

    @Override
    public void extractInformation() {
        // ADD YOUR CODE BELOW THIS
        MyHashTable<String, MyHashTable<String, Integer>> data = new MyHashTable<>();
        for (String[] line : this.parser.data) {
            String rawComments = line[this.parser.fields.get("comments")];
            String rawGender = line[this.parser.fields.get("gender")];
            String gender = rawGender.strip().toUpperCase();
            String[] splitRawComments = rawComments.strip().toLowerCase().replaceAll("[^a-zA-Z']", " ").split(" ");
            MyHashTable<String, Void> keywords = new MyHashTable<>();
            for (String keyword : splitRawComments) {
                if (!keyword.isEmpty()) {
                    keywords.put(keyword, null);
                }
            }
            for (MyPair<String, Void> pair : keywords) {
                String keyword = pair.getKey();
                if (data.get(keyword) == null) {
                    data.put(keyword, new MyHashTable<>());
                    for (String g : new String[]{"F", "M", "X"}) {
                        data.get(keyword).put(g, 0);
                    }
                }
                data.get(keyword).put(gender, data.get(keyword).get(gender) + 1);
            }
            this.data = data;
        }
        // ADD YOUR CODE ABOVE THIS
    }

}
