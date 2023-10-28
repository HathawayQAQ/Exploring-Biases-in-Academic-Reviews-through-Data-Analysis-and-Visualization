public class RatingDistributionByProf extends DataAnalyzer {

    private MyHashTable<String, MyHashTable<String, Integer>> data;

    public RatingDistributionByProf(Parser p) {
        super(p);
    }

    @Override
    public MyHashTable<String, Integer> getDistByKeyword(String rawProfessorName) {
        // ADD YOUR CODE BELOW THIS
        String professorName = rawProfessorName.strip().toLowerCase();
        return this.data.get(professorName);
        // ADD YOUR CODE ABOVE THIS
    }

    @Override
    public void extractInformation() {
        // ADD YOUR CODE BELOW THIS
        MyHashTable<String, MyHashTable<String, Integer>> data = new MyHashTable<>();
        for (String[] line : this.parser.data) {
            String rawProfessorName = line[this.parser.fields.get("professor_name")];
            String rawStudentStar = line[this.parser.fields.get("student_star")];
            String professorName = rawProfessorName.strip().toLowerCase();
            String studentStar = String.valueOf((int) Double.parseDouble(rawStudentStar.strip()));
            if (data.get(professorName) == null) {
                data.put(professorName, new MyHashTable<>());
                for (int i = 1; i <= 5; i++) {
                    data.get(professorName).put(String.valueOf(i), 0);
                }
            }
            data.get(professorName).put(studentStar, data.get(professorName).get(studentStar) + 1);
        }
        this.data = data;
        // ADD YOUR CODE ABOVE THIS
    }

}
