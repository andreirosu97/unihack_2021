import React from 'react';
import { createDrawerNavigator } from '@react-navigation/drawer'
import { NavigationContainer } from '@react-navigation/native';
import { MainScreen } from './screens/MainScreen';
import { Text, Button } from "react-native";
import { ContactScreen } from './screens/ContactScreen';

const Drawer = createDrawerNavigator();

const DrawerNavigation = () => {
  return <Drawer.Navigator initialRouteName={'MainScreen'}>
    <Drawer.Screen name={'MainScreen'} component={MainScreen}  options={{
          headerTitle: () => <Text>Titlu</Text>,
          headerRight: () => (
            <Button
              onPress={() => alert('This is a button!')}
              title="Info"
              color="#AAAAAA"
            />
          ),
        }}/>
    <Drawer.Screen name={'ContactScreen'} component={ContactScreen}/>
  </Drawer.Navigator>
}

export default function App() {
  return (
  <NavigationContainer>
    <DrawerNavigation/>
  </NavigationContainer>
  );
}
