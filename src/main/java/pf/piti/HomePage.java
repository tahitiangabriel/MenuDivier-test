package pf.piti;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	/** For the form. */
	private String name;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		// Form
		final Form<Void> form = new BootstrapForm<Void>("form");
		add(form);
		form.add(new TextField<String>("name", new PropertyModel<String>(this, "name")));

	}

	/**
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new HtmlTag("html-tag", getLocale()));
		add(new OptimizedMobileViewportMetaTag("viewport"));
		add(newNavbar("navbar"));
	}

	/**
	 * creates a new {@link Navbar} instance.
	 * 
	 * @param markupId The components markup id.
	 * @return a new {@link Navbar} instance
	 */
	protected Navbar newNavbar(final String markupId) {
		final Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.TOP);
		navbar.setInverted(false);

		// show brand name
		navbar.brandName(Model.of("MenuDivier"));

		// dropdown
		final NavbarDropDownButton dropDownMenu = new NavbarDropDownButton(Model.of("Level1")) {
			/** serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String varButtonMarkupId) {
				final List<AbstractLink> links = new ArrayList<AbstractLink>();

				links.add(new MenuDivider());

				final MenuBookmarkablePageLink<HomePage> link1 =
				        new MenuBookmarkablePageLink<HomePage>(HomePage.class, Model.of("Home"));
				link1.setIconType(GlyphIconType.home);
				links.add(link1);

				links.add(new MenuDivider());

				return links;
			}
		};
		dropDownMenu.setIconType(GlyphIconType.arrowdown);

		// les ajoute dans la barre de menus à gauche
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, dropDownMenu));

		return navbar;
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		Bootstrap.renderHead(response);
	}
}
