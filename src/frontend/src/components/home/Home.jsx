import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthProvider";
import { Client } from "../../client/Client";
import { Button, Table } from "semantic-ui-react";
import { parseJwt } from "../helpers/Utils";

const Home = () => {
  const [students, setStudents] = useState();
  const { getUser, user } = useAuth();

  useEffect(() => {
    handleGetStudents();
  }, []);

  const handleGetStudents = () => {
    Client.getStudents(getUser())
      .then((response) => {
        console.log(user.data.sub);
        setStudents(response.data);
      })
      .catch((error) => {
        console.log(error.request);
      });
  };

  function studentTable() {
    if (students === undefined || students.length === 0) {
      // console.log(parseJwt(user.data));
      return (
        <Table.Row key="1">
          <Table.Cell collapsing textAlign="center">
            No Students
          </Table.Cell>
        </Table.Row>
      );
    } else {
      return students.map((std) => {
        return (
          <Table.Row key={std.id}>
            <Table.Cell collapsing>
              <Button circular color="red" size="small" icon="trash" />
            </Table.Cell>
            <Table.Cell>{std.firstname}</Table.Cell>
            <Table.Cell>{std.lastname}</Table.Cell>
            <Table.Cell>{std.year}</Table.Cell>
          </Table.Row>
        );
      });
    }
  }

  return (
    <Table compact striped selectable>
      <Table.Header>
        <Table.Row>
          <Table.HeaderCell width={1} />
          <Table.HeaderCell>Firstname</Table.HeaderCell>
          <Table.HeaderCell>Lastname</Table.HeaderCell>
          <Table.HeaderCell>Year</Table.HeaderCell>
        </Table.Row>
      </Table.Header>
      <Table.Body>{studentTable()}</Table.Body>
    </Table>
  );
};

export default Home;
