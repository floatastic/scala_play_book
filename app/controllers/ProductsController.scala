package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import models.Product
import play.api.i18n.{I18nSupport, MessagesApi}

@Singleton
class ProductsController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }

  def show(ean: Long) = Action { implicit request =>
    Product.findByEan(ean).map { product =>
      Ok(views.html.products.details(product))
    }.getOrElse(NotFound)
  }
}
