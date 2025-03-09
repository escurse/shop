package com.escass.shop.Controllers;

import com.escass.shop.DTO.CustomUser;
import com.escass.shop.Entities.CommentEntity;
import com.escass.shop.Entities.ItemEntity;
import com.escass.shop.Repositories.CommentRepository;
import com.escass.shop.Repositories.ItemRepository;
import com.escass.shop.Services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView list() {
        List<ItemEntity> result = itemRepository.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("items", result);
        return modelAndView;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView write() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("write");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView add(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "price", required = false) Integer price, @RequestParam String username) {
        // @RequestParam Map<String, String> params MAP으로 해서 값 받아오기 가능
        itemService.saveItem(title, price, username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView detail(@PathVariable Long id) {
        Optional<ItemEntity> items = itemService.getItemById(id);
        List<CommentEntity> comments = itemService.findAllCommentByParentId(id);
        if (items.isPresent()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("detail");
            modelAndView.addObject("item", items.get());
            modelAndView.addObject("comments", comments);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView update(@PathVariable Long id) {
        Optional<ItemEntity> items = itemService.getItemById(id);
        if (items.isPresent()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("update");
            modelAndView.addObject("item", items.get());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @RequestMapping(value = "/replace/{id}", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView replace(@PathVariable Long id, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "price", required = false) Integer price) {
        itemService.updateItemById(id, title, price);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @GetMapping("/test1")
    String test1(@RequestParam String name, @RequestParam Integer age) {
        System.out.println(name);
        System.out.println(age);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@RequestBody Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/list/page/{id}")
    String getListPage(Model model, @PathVariable Integer id) {
        Page<ItemEntity> result = itemRepository.findPageBy(PageRequest.of(id-1, 5));
        model.addAttribute("total", result.getTotalPages());
        model.addAttribute("items", result);
        return "/list";
    }

    @PostMapping("/comment/{id}")
    String postComment(Authentication auth, @PathVariable Integer id, @RequestParam String content) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        itemService.writeComment(user.getUsername(), content, id);
        return "redirect:/detail/{id}";
    }

    @PostMapping("/search")
    String postSearch(@RequestParam String search) {
        var result = itemRepository.findAllByTitleContains(search);
        System.out.println(result);
        return "redirect:/list/page/1";
    }
}
