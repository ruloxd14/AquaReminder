package molina.raul.aquareminder.ui.notifications

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import molina.raul.aquareminder.InicioSesionActivity
import molina.raul.aquareminder.R
import molina.raul.aquareminder.RecipienteActivity
import molina.raul.aquareminder.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textNotifications
        //notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
        //textView.text = it
        // })

        /*val nombre = getActivity()?.getIntent()?.getExtras()?.getString("name")
        if (nombre != null) {
            tv_nombreperfil.setText(nombre)
        }
        val nombre = requireArguments().getString("name")
        if(nombre != null){
            tv_nombreperfil.setText(nombre)
        }
        val nombre = requireArguments().getString("nombre")
        tv_nombreperfil.text = nombre
        */
        val arg = arguments
        if(arg != null){
            val name = arg.getString("name")
            root.tv_nombreperfil.setText(name)
        }




        root.findViewById<Button>(R.id.btn_configuraciones).setOnClickListener {

        }
        root.findViewById<Button>(R.id.btn_unidades).setOnClickListener {

        }
        root.findViewById<Button>(R.id.btn_idiomas).setOnClickListener {

        }
        root.findViewById<Button>(R.id.btn_datos_per).setOnClickListener {

        }
        root.findViewById<Button>(R.id.btn_recipientes).setOnClickListener {
            val myIntent = Intent(activity, RecipienteActivity::class.java)
            requireActivity().startActivity(myIntent)
        }
        root.findViewById<Button>(R.id.btn_cerrar_sesion).setOnClickListener {
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}