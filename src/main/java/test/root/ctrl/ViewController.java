package test.root.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import test.root.entities.User;
import test.root.entities.UserRepository;
import test.root.entities.services.UserService;

/**
 * Created by Filip on 2015-07-31.
 */
@Controller
public class ViewController {


    @RequestMapping(value={"/", "/login"})  ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.setViewName("homeView");

        return model;
    }

    @RequestMapping(value={"/book-editor"})  ModelAndView bookEditor() {
        ModelAndView model = new ModelAndView();
        model.setViewName("editorView");
        return model;
    }
}
