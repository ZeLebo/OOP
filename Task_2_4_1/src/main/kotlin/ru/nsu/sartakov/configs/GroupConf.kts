import ru.nsu.sartakov.dsl.DSL

DSL().group {
    number = 20214
    students {
        student {
            nickName = "ZeLebo"
            firstName = "Alexander"
            lastName = "Sartakov"
            url = "https://github.com/ZeLebo/OOP.git"

            givenTasks("Task_1_1_1", "Task_1_1_2")

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
            nickName = "ZeLebo"
            firstName = "Alexander"
            lastName = "Sartakov"
            url = ""
        }
    }
}
