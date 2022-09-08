# Валидатор данных
  Данный проект реализует возможность валидирования данных при помощи создания схем, которые проверяют данные определенного типа на соотвествия заданным условиям.
# Примеры
  Валидация строк:
  ```
  Validator v = new Validator();

  StringSchema schema = v.string();

  schema.isValid(""); // true
  schema.isValid(null); // true

  schema.required();

  schema.isValid("what does the fox say"); // true
  schema.isValid("hexlet"); // true
  schema.isValid(null); // false
  schema.isValid(""); // false

  schema.contains("wh").isValid("what does the fox say"); // true
  schema.contains("what").isValid("what does the fox say"); // true
  schema.contains("whatthe").isValid("what does the fox say"); // false

  schema.isValid("what does the fox say"); // false
  ```
  Валидация целочисленных значений:
  ~~~
  Validator v = new Validator();

  NumberSchema schema = v.number();

  schema.isValid(null); // true

  schema.required();

  schema.isValid(null); // false
  schema.isValid(10) // true
  schema.isValid("5"); // false

  schema.positive().isValid(10); // true
  schema.isValid(-10); // false

  schema.range(5, 10);

  schema.isValid(5); // true
  schema.isValid(10); // true
  schema.isValid(4); // false
  schema.isValid(11); // false
  ~~~
  Валидация хранилища пар типа ключ-значение:
  ```
  Validator v = new Validator();

  MapSchema schema = v.map();

  schema.isValid(null); // true

  schema.required();

  schema.isValid(null) // false
  schema.isValid(new HashMap()); // true
  Map<String, String> data = new HashMap<>();
  data.put("key1", "value1");
  schema.isValid(data); // true

  schema.sizeof(2);

  schema.isValid(data);  // false
  data.put("key2", "value2");
  schema.isValid(data); // true
  ```
### Hexlet tests and linter status:
[![Actions Status](https://github.com/datfeelbruh/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/datfeelbruh/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/025d761e8f95ac1ce400/maintainability)](https://codeclimate.com/github/datfeelbruh/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/025d761e8f95ac1ce400/test_coverage)](https://codeclimate.com/github/datfeelbruh/java-project-78/test_coverage)
