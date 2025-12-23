package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.models.InquiryForm;
import com.example.demo.repositries.InquiryRepository;

@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	InquiryRepository repository;

	@GetMapping
	public String index() {
		return "root/index";
	}
	//form1
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm,
	                   BindingResult bindingResult,
	                   RedirectAttributes redirectAttributes) {

	    if (bindingResult.hasErrors()) {
	        return "root/form"; // エラー時はPRGしない
	    }

	    repository.saveAndFlush(inquiryForm);

	    redirectAttributes.addFlashAttribute(
	        "message", "お問い合わせを受け付けました。"
	    );

	    return "redirect:/form";
	}

	

	//form2
	@GetMapping("/form2")
	public String form2(InquiryForm inquiryForm) {
		return "root/form2"; // form2.html を返す
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm inquiryForm,
	                    BindingResult bindingResult,
	                    RedirectAttributes redirectAttributes) {

	    if (bindingResult.hasErrors()) {
	        return "root/form2";
	    }

	    repository.saveAndFlush(inquiryForm);

	    redirectAttributes.addFlashAttribute(
	        "message", "お問い合わせ2を受け付けました。"
	    );

	    return "redirect:/form2";
	}


	// 管理画面：一覧表示 (Read)
	@GetMapping("/admin/list")
	public String list(Model model) {
		// 全データを取得してモデルに積む
		List<InquiryForm> inquiries = repository.findAll();
		model.addAttribute("inquiries", inquiries);
		return "admin/list"; // templates/admin/list.html を作成する
	}

	// 管理画面：削除機能 (Delete)
	@PostMapping("/admin/delete/{id}")
	public String delete(@PathVariable Long id) {
		// IDを指定して削除
		repository.deleteById(id);
		// 削除後は一覧画面へリダイレクト（再表示）
		return "redirect:/admin/list";
	}

	// 1. 編集画面を表示する (Read for Update)
	@GetMapping("/admin/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		// IDで検索し、存在しなければ一覧へ戻す
		InquiryForm inquiryForm = repository.findById(id).orElse(null);
		if (inquiryForm == null) {
			return "redirect:/admin/list";
		}
		model.addAttribute("inquiryForm", inquiryForm);
		return "admin/edit"; // templates/admin/edit.html を作成する
	}

	// 2. 更新を実行する (Update)
	@PostMapping("/admin/update/{id}")
	public String update(@PathVariable Long id, @Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/edit";
		}

		// IDをセットして保存することで、新規登録ではなく「更新」になる
		inquiryForm.setId(id); 
		repository.saveAndFlush(inquiryForm);

		model.addAttribute("message", "お問い合わせ内容を更新しました。");
		return "redirect:/admin/list"; // 更新後は一覧へ
	}
}