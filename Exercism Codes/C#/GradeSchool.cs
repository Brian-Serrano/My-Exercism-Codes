using System;
using System.Collections.Generic;
using System.Linq;
public class GradeSchool
{
    private IDictionary<string, int> students = new Dictionary<string, int>();
    public bool Add(string student, int grade) => students.TryAdd(student, grade);
    public IEnumerable<string> Roster() => students.OrderBy(x => x.Value).ThenBy(x => x.Key).Select(x => x.Key);
    public IEnumerable<string> Grade(int grade) => students.Where(e => e.Value == grade).Select(e => e.Key).OrderBy(x => x);
}