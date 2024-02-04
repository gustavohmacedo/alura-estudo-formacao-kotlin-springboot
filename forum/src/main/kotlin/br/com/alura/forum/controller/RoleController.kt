package br.com.alura.forum.controller

import br.com.alura.forum.dto.RoleResponseDTO
import br.com.alura.forum.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/role")
class RoleController(
    private val roleService: RoleService,
) {

    @PostMapping("/save")
    fun createRole(@RequestHeader(value = "role") role: String): ResponseEntity<RoleResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(roleService.saveRole(role))
    }

    @GetMapping("/{roleId}")
    fun getRole(@PathVariable roleId: Long): ResponseEntity<RoleResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(roleService.getRole(roleId))
    }

    @GetMapping
    fun getAllRole(): ResponseEntity<List<RoleResponseDTO>>{
        return ResponseEntity
            .status(HttpStatus.OK).body(roleService.getAllRole())

    }

    @DeleteMapping("/delete")
    fun deleteRole(@RequestHeader(value = "roleId") roleId: Long): ResponseEntity<String> {
        roleService.deleteRoleById(roleId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Role excluída com sucesso!")

    }

}