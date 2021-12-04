import React, { useEffect, useState } from "react";
import {
  Text,
  StyleSheet,
  View,
  ScrollView,
  Picker,
  Button,
  ActivityIndicator,
} from "react-native";
import { CustomButton } from "../components/CustomButton";
import CollapseView from "../components/CollapseView";

function getTitle(text) {
  const title =
    text.split(" ").reduce((accumulator, currentWord) => {
      if (accumulator.length < 150) {
        accumulator += currentWord + " ";
      }
      return accumulator;
    }, "") + "...";
  return title.charAt(0).toUpperCase() + title.slice(1);
}

export const MainScreen = ({ navigation }) => {
  console.log(new Date(), "render");
  const [an, setAn] = useState(2021);
  const [decisions, setDecisions] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    const loadAnualDecisions = async () => {
      const response = await fetch(
        `https://xpage.primariatm.ro/lotusweb.nsf/hcl.xsp/hcl/?an=${an}`
      );
      const decisions = await response.json();
      setDecisions(decisions);
      setIsLoading(false);
    };
    setIsLoading(true);
    loadAnualDecisions();
  }, [an]);

  return (
    <ScrollView style={styles.container} styleContent={styles.contentContainer}>
      <Text style={styles.text}>Test {an} </Text>
      <Picker
        selectedValue={an}
        style={{ height: 50, width: 150 }}
        onValueChange={(itemValue) => setAn(itemValue)}
      >
        {[2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021].map(
          (year) => (
            <Picker.Item label={`Anul ${year}`} value={year} key={year} />
          )
        )}
      </Picker>
      {isLoading ? (
        <ActivityIndicator size={"large"} color={"gray"} />
      ) : (
        decisions.map((d) => {
          return (
            <CollapseView
              text={d.privind}
              unfilled
              key={d.unid_hcl}
              title={getTitle(d.privind)}
              style={{
                margin: 20,
                backgroundColor: "white",
                borderWidth: 1,
                borderColor: "black",
              }}
            />
          );
        })
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  text: {
    fontSize: 24,
    color: "red",
  },
  contentContainer: {
    alignItems: "center",
    justifyContent: "center",
  },
});

//   <Button title={"increment"} onPress={() => {
//     setAn(an + 1);
// }}/>
