# Spring MVC

## Annotations:
- **@EnableWebMvc** - enable web mvc (before config)
```java
@Configuration
@ComponentScan("org.bonnysid.bloom")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {}
```
- **@Controller** - show to spring: this class is controller
```java
@Controller
public class UsersController {
    private final UserDAO userDAO;
}
```
- **@GetMapping / @PostMapping / @PatchMapping / @DeleteMapping** _(props: "/url" or "/urlParams/{id}");
```java
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String get(Model model) {
        model.addAttribute("users", userDAO.get());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String getFormForCreate(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/new";
        userDAO.insert(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String getFormForCreateUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) return "users/update";
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }

}
```
- **@RequestMapping** _(props: "baseUrl", before Controller)_ - set base url for mapping request
```java
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;
}
```
- **@PathVariable** _(props: "param")_ - get param in url
```java
    @GetMapping("/{id}/update")
    public String getFormForCreateUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/update";
    }
```
- **@Valid** - show param with validation
```java
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/new";
        userDAO.insert(user);
        return "redirect:/users";
    }
```
- **@ModelAttribute** _(props: "paramName")_ - can get object from model
```java
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) return "users/update";
        userDAO.update(id, user);
        return "redirect:/users";
    }
```
- **@NotEmpty** _(props: message)_ - _validator_ for fields
```java
    @NotEmpty(message = "Login is required")
    private String login;
```
- **@Size** _(props: min, max, message)_ - _validator_ for fields
```java
    @NotEmpty(message = "Login is required")
    @Size(min = 2, max = 30, message = "Login length should be between 2-30")
    private String login;
```
- **@Email** _(props: message)_ - _validator_ for emails
```java
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be incorrect")
    private String email;
```
- **@Min** _(props: min, message)_ - _validator_ for fields
```java
    @Min(value = 0, message = "Age cannot be less than 0")
    private int age;
```

## Objects / Interfaces / Classes:
- **WebMvcConfigurer** (_interface_) - for config
- **Model** - for communicate with page
```java
    @GetMapping()
    public String get(Model model) {
        model.addAttribute("users", userDAO.get());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/user";
    }
```
- **BindingResult** (_object_) - after prop with **@Valid**
```java
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/new";
        userDAO.insert(user);
        return "redirect:/users";
    }
```
- **RowMapper<T>** - for obj mappers
```java
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setId(resultSet.getInt("id"));
        u.setLogin(resultSet.getString("login"));
        u.setEmail(resultSet.getString("email"));
        u.setAge(resultSet.getInt("age"));
        return u;
    }
}
```
- **DataSource** - using for **JDBC Template**
```java
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DataConfig.URL.value());
        dataSource.setUsername(DataConfig.USERNAME.value());
        dataSource.setPassword(DataConfig.PASSWORD.value());

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
```
- **JdbcTemplate** - for query to DB
```java
@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> get() {
        return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }

    public User get(int id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO Users (login, email, password, age) VALUES (?, ?, ?, ?)", user.getLogin(), user.getEmail(), user.getPassword(), user.getAge());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id=?", id);

    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE Users SET login=?, email=?, password=?, age=?  WHERE id=?", user.getLogin(), user.getEmail(), user.getPassword(), user.getAge(), id);
    }
}
```
