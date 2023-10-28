//import java.math.RoundingMode;
//import java.text.DecimalFormat;

public class RatingDistributionBySchool extends DataAnalyzer {

    private MyHashTable<String, MyHashTable<String, Integer>> data;

    public RatingDistributionBySchool(Parser p) {
        super(p);
    }

    @Override
    public MyHashTable<String, Integer> getDistByKeyword(String rawSchoolName) {
        // ADD YOUR CODE BELOW THIS
        String schoolName = rawSchoolName.strip().toLowerCase();
        return this.data.get(schoolName);
        // ADD YOUR CODE ABOVE THIS
    }

    @Override
    public void extractInformation() {
        // ADD YOUR CODE BELOW THIS
        MyHashTable<String, MyHashTable<String, Integer>> countData = new MyHashTable<>();
        MyHashTable<String, MyHashTable<String, Double>> studentStarSumData = new MyHashTable<>();
        MyHashTable<String, MyHashTable<String, Integer>> data = new MyHashTable<>();
        for (String[] line : this.parser.data) {
            String rawProfessorName = line[this.parser.fields.get("professor_name")];
            String rawSchoolName = line[this.parser.fields.get("school_name")];
            String rawStudentStar = line[this.parser.fields.get("student_star")];
            String professorName = rawProfessorName.strip().toLowerCase();
            String schoolName = rawSchoolName.strip().toLowerCase();
            double studentStar = Double.parseDouble(rawStudentStar.strip());
            if (countData.get(schoolName) == null) {
                countData.put(schoolName, new MyHashTable<>());
                studentStarSumData.put(schoolName, new MyHashTable<>());
                data.put(schoolName, new MyHashTable<>());
            }
            if (countData.get(schoolName).get(professorName) == null) {
                countData.get(schoolName).put(professorName, 0);
                studentStarSumData.get(schoolName).put(professorName, 0.0);
            }
            countData.get(schoolName).put(
                    professorName, countData.get(schoolName).get(professorName) + 1);
            studentStarSumData.get(schoolName).put(
                    professorName, studentStarSumData.get(schoolName).get(professorName) + studentStar);
        }
        for (MyPair<String, MyHashTable<String, Integer>> outerPair : countData) {
            String schoolName = outerPair.getKey();
            for (MyPair<String, Integer> innerPair : outerPair.getValue()) {
                String professorName = innerPair.getKey();
                // DecimalFormat format = new DecimalFormat("0.0#");
                // String.format("%.2f", number);
               // format.setRoundingMode(RoundingMode.HALF_UP);
                String studentStarAverage = String.format("%.2f", studentStarSumData.get(schoolName).get(professorName)
                        / countData.get(schoolName).get(professorName));// format.format(studentStarSumData.get(schoolName).get(professorName)
                // / countData.get(schoolName).get(professorName));
                data.get(schoolName).put(
                        professorName + "\n" + studentStarAverage, countData.get(schoolName).get(professorName));
            }
        }
        this.data = data;
        // ADD YOUR CODE ABOVE THIS
    }
}
