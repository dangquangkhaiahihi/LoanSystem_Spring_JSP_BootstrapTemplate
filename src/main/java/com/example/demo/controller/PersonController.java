//package com.example.demo.controller;
//
//import com.example.demo.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class PersonController {
//    @Autowired
//    PersonService personService;
//
//    @PostMapping(value = "")
//    @ResponseBody
//    public ResponseEntity<ResponseDTO> createUpdate(@Valid @RequestBody PersonDTO personDTO,
//                                                    HttpServletRequest request) {
//        return ResponseEntity.ok().body(ResponseUtils.responseOK(personService.createUpdate(personDTO)));
//    }
//
//    @PostMapping(value = "/find")
//    @ResponseBody
//    public ResponseEntity<ResponseDTO> findList(@RequestParam(value = "name", required = false) String name,
//                                                @RequestParam(value = "address", required = false) String address,
//                                                @RequestParam(value = "phone", required = false) String phone,
//                                                @RequestParam(value = "email", required = false) String email,
//                                                @RequestParam(value = "userId", required = false) Long userId,
//                                                @RequestParam(value = "fromId", required = false) Long fromId,
//                                                @RequestParam(value = "toId", required = false) Long toId,
//                                                @RequestParam(value = "fromTotal", required = false) Long fromTotal,
//                                                @RequestParam(value = "toTotal", required = false) Long toTotal,
//                                                @RequestParam(value = "fromCreatedDate", required = false) Instant fromCreatedDate,
//                                                @RequestParam(value = "toCreatedDate", required = false) Instant toCreatedDate,
//                                                @RequestParam(value = "fromLastModifiedDate", required = false) Instant fromLastModifiedDate,
//                                                @RequestParam(value = "toLastModifiedDate", required = false) Instant toLastModifiedDate,
//                                                @RequestParam(defaultValue = "0") int page,
//                                                @RequestParam(defaultValue = "10") int size) {
//        Page<PersonEntity> pageResult = personService.findList(name, address, phone, email, userId,
//                fromId, toId, fromTotal, toTotal,
//                fromCreatedDate, toCreatedDate,
//                fromLastModifiedDate, toLastModifiedDate,
//                PageRequest.of(page, size));
//        return ResponseEntity.ok().body(ResponseUtils.responseOK(pageResult));
//    }
//}
