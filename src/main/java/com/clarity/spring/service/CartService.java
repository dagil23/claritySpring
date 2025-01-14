package com.clarity.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarity.spring.model.LineaPedido;
import com.clarity.spring.model.Pedido;
import com.clarity.spring.model.Pedido.EstadoPedido;
import com.clarity.spring.model.Producto;
import com.clarity.spring.model.Usuario;
import com.clarity.spring.repository.OrderLineRepository;
import com.clarity.spring.repository.OrderRepository;
import com.clarity.spring.repository.ProductRepository;
import com.clarity.spring.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderLineRepository orderLineRepository;

	
	public Pedido crearNuevoCarrito(Usuario usuario, Long idProducto) {
		
		Producto producto = productRepository.findByProductoId(idProducto);
		Pedido pedidoNuevo = new Pedido();
		pedidoNuevo.setUsuario(usuario);
		pedidoNuevo.setNombre(usuario.getNombre());
		pedidoNuevo.setApellidos(usuario.getApellido());
		pedidoNuevo.setDireccion(usuario.getDireccion());
		pedidoNuevo.setDni(usuario.getDni());
		pedidoNuevo.setEstado(EstadoPedido.Carrito);
		pedidoNuevo.setPrecio(producto.getPrecio());

		return orderRepository.save(pedidoNuevo);
		
	}
	
	public void agregarProdutoAcarrito(String username, Long idProducto) {
		
		Usuario usuario = userRepository.findByEmail(username);
		if(usuario == null) { throw new IllegalArgumentException("Usuario no encontrado"); }
		
		Producto producto = productRepository.findByProductoId(idProducto);
		if(producto == null) {throw new IllegalArgumentException("Producto no encontrado"); }
		
			
		Pedido pedido = orderRepository.findByUsuarioAndEstado(usuario, EstadoPedido.Carrito);
		if(pedido == null){crearNuevoCarrito(usuario, producto.getProducto_id()); }
					
					 
		LineaPedido lineaPedido = orderLineRepository.findByPedidoAndProducto(pedido.getId_pedido(),idProducto);	
		if(lineaPedido == null) {
			
		}
		 orderRepository.save(pedido);
		 
	}
	
	
	
}