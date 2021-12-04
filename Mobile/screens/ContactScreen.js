import React, { useState } from "react";
import { Text, StyleSheet, View, Button } from "react-native";

export const ContactScreen = ({navigation}) => { 
    return <View style={styles.container}>
        <Text style={styles.text}>Salut Contact Screen!</Text>
    </View>
}



const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
        fontSize:24,
        color:"red"
    }
  });