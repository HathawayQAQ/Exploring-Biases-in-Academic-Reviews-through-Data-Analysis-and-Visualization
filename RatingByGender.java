public class RatingByGender extends DataAnalyzer {

    private MyHashTable<String, MyHashTable<String, Integer>> data;

    public RatingByGender(Parser p) {
        super(p);
    }

    @Override
    public MyHashTable<String, Integer> getDistByKeyword(String rawInput) {
        // ADD YOUR CODE BELOW THIS
        String[] splitRawInput = rawInput.strip().split(", ");
        if (splitRawInput.length != 2) {
            return null;
        }
        String gender = splitRawInput[0].toUpperCase();
        String type = splitRawInput[1].toLowerCase();
        String input = gender + ", " + type;
        return this.data.get(input);
        // ADD YOUR CODE ABOVE THIS
    }

    @Override
    public void extractInformation() {
        // ADD YOUR CODE BELOW THIS
        MyHashTable<String, MyHashTable<String, Integer>> studentStarData = new MyHashTable<>();
        MyHashTable<String, MyHashTable<String, Integer>> studentDifficultData = new MyHashTable<>();
        MyHashTable<String, MyHashTable<String, Integer>> data = new MyHashTable<>();
        for (String g : new String[]{"F", "M", "X"}) {
            studentStarData.put(g, new MyHashTable<>());
            studentDifficultData.put(g, new MyHashTable<>());
            for (int i = 1; i <= 5; i++) {
                studentStarData.get(g).put(String.valueOf(i), 0);
                studentDifficultData.get(g).put(String.valueOf(i), 0);
            }
        }
        for (String[] line : this.parser.data) {
            String rawStudentStar = line[this.parser.fields.get("student_star")];
            String rawStudentDifficult = line[this.parser.fields.get("student_difficult")];
            String rawGender = line[this.parser.fields.get("gender")];
            String studentStar = String.valueOf((int) Double.parseDouble(rawStudentStar.strip()));
            String studentDifficult = String.valueOf((int) Double.parseDouble(rawStudentDifficult.strip()));
            String gender = rawGender.strip().toUpperCase();;
            studentStarData.get(gender).put(
                    studentStar, studentStarData.get(gender).get(studentStar) + 1);
            studentDifficultData.get(gender).put(
                    studentDifficult, studentDifficultData.get(gender).get(studentDifficult) + 1);
        }
        for (String gender : new String[]{"F", "M"}) {
            data.put(gender + ", quality", studentStarData.get(gender));
            data.put(gender + ", difficulty", studentDifficultData.get(gender));
        }
        this.data = data;
        // ADD YOUR CODE ABOVE THIS
    }
}
