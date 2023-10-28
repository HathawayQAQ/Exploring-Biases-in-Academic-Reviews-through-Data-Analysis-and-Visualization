public class RatingByKeyword extends DataAnalyzer {

    private MyHashTable<String, MyHashTable<String, Integer>> data;

    public RatingByKeyword(Parser p) {
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
            String rawStudentStar = line[this.parser.fields.get("student_star")];
            String studentStar = String.valueOf((int) Double.parseDouble(rawStudentStar.strip()));
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
                    for (int i = 1; i <= 5; i++) {
                        data.get(keyword).put(String.valueOf(i), 0);
                    }
                }
                data.get(keyword).put(studentStar, data.get(keyword).get(studentStar) + 1);
            }
            this.data = data;
        }
        // ADD YOUR CODE ABOVE THIS
    }
}
