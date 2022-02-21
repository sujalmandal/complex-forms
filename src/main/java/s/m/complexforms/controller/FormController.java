package s.m.complexforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s.m.complexforms.dto.Input;
import s.m.complexforms.dto.Output;
import s.m.complexforms.statemachine.StateExecutionService;

@RestController
@RequestMapping("/state")
@CrossOrigin(origins = "*")
public class FormController {


    private StateExecutionService executionService;

    @Autowired
    public FormController(StateExecutionService executionService) {
        this.executionService = executionService;
    }

    @RequestMapping(value = "/process",method = RequestMethod.POST)
    public ResponseEntity<Output> executeState(@RequestBody Input input) {
        return ResponseEntity.ok(executionService.executeState(input));
    }

}
