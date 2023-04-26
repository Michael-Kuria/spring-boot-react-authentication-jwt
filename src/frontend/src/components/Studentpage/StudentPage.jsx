import { useEffect, useState } from "react";
import { getStudents } from "../../client/Login";

const StudentPage = ({ token }) => {
  const [students, setStudents] = useState([]);

  function fetchStudents() {
    getStudents(token)
      .then((result) => result.json())
      .then((data) => setStudents(data))
      .catch((error) => console.log(error));
  }

  useEffect(() => {
    fetchStudents();
  }, [token]);

  return (
    <section>
      <ul>
        {students.map((std, index) => (
          <li key={index}> {std.firstname}</li>
        ))}
      </ul>
    </section>
  );
};

export default StudentPage;
