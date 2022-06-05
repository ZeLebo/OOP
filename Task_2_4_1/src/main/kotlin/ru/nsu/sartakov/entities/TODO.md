Models
Entities
1. Описание задачи 
   - ID
   - Название
   - Количество баллов
2. Группа
   - Naming (Int)
3. Студент
   - Ник 
   - ФИО
     - Имя
     - Фамилия
   - URL
4. Выданная задача (идентификатор, deadline)
   - ID
   - Deadline
5. Контрольная отметка (Just Mark)
6. Занятия
   - Дата проведения
   - Посещаемость (для каждого студента)

Complex
1. Список задач
   - Add the task to the list
   - Remove the task from the list
2. Группа студентов
   - Add student to the group
   - Remove student from the group
3. План выполнения (какие задачи назначены, со сроками сдачи)
   - Let it be planned
4. Общие настройки системы
   - HZ what is it