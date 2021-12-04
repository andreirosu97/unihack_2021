import * as React from "react";
import { useEffect, useState, useRef } from "react";
import { Text, View, StyleSheet, Button, Animated } from "react-native";
import { Color } from "../constants/Colors";
import { IconButton } from "react-native-paper";

const CollapseView = ({ text }) => {
  const [collapsed, setCollapsed] = useState(true);
  const [maxLines, setMaxLines] = useState(2);
  const animationHeight = useRef(new Animated.Value(0)).current;

  const toggleCollapsed = () => {
    setCollapsed(!collapsed);
  };

  const collapseView = () => {
    Animated.timing(animationHeight, {
      duration: 0,
      toValue: 80,
    }).start();
  };

  const expandView = () => {
    setMaxLines(null);
    Animated.timing(animationHeight, {
      duration: 0,
      toValue: 1000,
    }).start();
  };

  useEffect(() => {
    if (collapsed) {
      collapseView();
    } else {
      expandView();
    }
  }, [collapsed]);

  return (
    <View style={unfilledStyle.container}>
      <Animated.View style={{ maxHeight: animationHeight }}>
        <Text style={styles.paragraph} numberOfLines={maxLines}>
          {text}
        </Text>
      </Animated.View>
      <IconButton
        // icon={require("../assets/arrowhead01.png")}
        icon={"camera"}
        color={Color.black}
        size={20}
        onPress={() => toggleCollapsed()}
      />
      {/* <Button
        title="lolol"
        icon={require("../assets/icon.png")}
        onPress={toggleCollapsed}
      /> */}
    </View>
  );
};

const styles = StyleSheet.create({
  paragraph: {
    margin: 24,
    fontSize: 14,
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
    borderBottomWidth: 1,
    borderColor: "lightgray",
  },
  text: {
    color: Color.black,
    fontWeight: "500",
  },
});

export default CollapseView;
