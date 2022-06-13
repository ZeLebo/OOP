import ru.nsu.sartakov.dsl.DSL

DSL().groups {
    group {
        number = 20214
        students {
            student {
                nickName = "ZeLebo"
                firstName = "Alexander"
                lastName = "Sartakov"
                url = "https://github.com/ZeLebo/OOP.git"

                givenTasks("Task_1_1_1", "Task_1_1_2", "Task_1_4_2",
                    "Task_2_1_1", "Task_2_2_1", "Task_1_2_2", "Task_1_3_2")

                marks {
                    mark {
                        value = 5
                        date = "01.01.2022"
                    }
                    mark {
                        value = 4
                        date = "01.01.2022"
                    }
                }
            }
            student {
                nickName = "PeachMood"
                firstName = "Anna"
                lastName = "Voronova"
                url = "https://github.com/PeachMood/OOP.git"

                givenTasks("Task_2_1_1", "Task_2_1_2")

                marks {
                    mark {
                        value = 5
                        date = "01.01.2022"
                    }
                    mark {
                        value = 4
                        date = "01.01.2022"
                    }
                }
            }
            student {
                nickName = "L3XxXa"
                firstName = "Alexey"
                lastName = "Malov"
                url = "https://github.com/L3XxXa/OOP.git"

                givenTasks("task_1_2_2", "task_1_4_2", "task_2_1_1", "task_2_3_1")

                marks {
                    mark {
                        value = 5
                        date = "01.01.2022"
                    }
                    mark {
                        value = 4
                        date = "01.01.2022"
                    }
                }
            }
        }
    }
}