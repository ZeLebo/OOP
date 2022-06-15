import ru.nsu.sartakov.dsl.DSL

DSL().tasks {
    task {
        taskId = "Task_1_1_1"
        deadLine = "01.01.2022"
        score = 5
    }
    task {
        taskId = "Task_1_1_2"
        score = 5
        deadLine = "01.01.2022"
    }
    task {
        taskId = "Task_2_1_1"
        score = 5
        deadLine = "01.01.2022"
    }
    task {
        taskId = "Task_2_1_2"
        score = 5
        deadLine = "01.01.2022"
    }
    task {
        taskId = "Task_1_2_1"
        score = 5
        deadLine = "01.01.2022"
    }
}