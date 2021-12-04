import React from "react";
import { Text, View, StyleSheet, Button } from "react-native";
import { Color } from "../constants/Colors";
import CollapseView from "./CollapseView";
import Card from "react-native-paper";

export const CustomButton = ({ unfilled, onPress, title }) => {
  const styles = unfilled ? unfilledStyle : filledStyle;

  return (
    <View style={styles.container}>
      <Text style={styles.text}>{title}</Text>
      {/* <Button title={"increment"} onPress={() => {}} /> */}
      <Card>
        <CollapseView />
      </Card>
    </View>
  );
};

const filledStyle = StyleSheet.create({
  container: {
    alignItems: "center",
    backgroundColor: Color.bostonBlue,
    borderColor: Color.bluemine,
    borderWidth: 2,
    borderRadius: 20,
    padding: 10,
    marginTop: 10,
    marginBottom: 10,
  },
  text: {
    color: Color.white,
    fontWeight: "bold",
  },
});

const unfilledStyle = StyleSheet.create({
  container: {
    backgroundColor: Color.white,
    padding: 10,
    elevation: 3,
    // marginTop: 10,
    // marginBottom: 10,
    height: 100,
    borderBottomWidth: 1,
    borderColor: "lightgray",
  },
  text: {
    color: Color.black,
    fontWeight: "500",
  },
});
